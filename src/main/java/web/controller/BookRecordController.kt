package web.controller

import bean.BookRecord
import bean.Site
import bean.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import service.BookRecordService
import service.MeetingRoomService
import service.SiteService
import service.TimeService
import java.text.SimpleDateFormat
import javax.servlet.http.HttpSession

/**
 * @author NIce
 * @date 2018-04-25 12:24
 */

@RequestMapping("bookRecord")
@Controller
class BookRecordController {
    @Autowired
    lateinit var bookRecordService: BookRecordService

    @Autowired
    lateinit var timeService: TimeService

    @Autowired
    lateinit var siteService: SiteService

    @Autowired
    lateinit var meetingRoomService: MeetingRoomService

    @RequestMapping("selectDetail")
    fun selectDetail(bookRecord: BookRecord, siteId: Int, modelMap: ModelMap): String {
        val details = bookRecordService.selectDetailByDateAndRoomId(bookRecord.date, bookRecord.meetingRoomId)
        val map = HashMap<Any?, Any?>(details.size)
        details.forEach { record ->
            val startTimeId = record["start_time_index"] as Int
            val endTimeId = record["end_time_index"] as Int
            for (i in startTimeId..endTimeId)
                map.put(i, record)
        }
        val meetingRoom = meetingRoomService.findById(bookRecord.meetingRoomId)
        val site: Site = siteService.selectById(siteId)
        val timeList = timeService.selectAll()
        modelMap.addAttribute("site", site)
        modelMap.addAttribute("meetingRoom", meetingRoom)
        modelMap.addAttribute("map", map)
        modelMap.addAttribute("timeList", timeList)
        return "meeting_room_detail"
    }

    @RequestMapping("toBookUI")
    fun toBooUI(bookRecord: BookRecord, timeIndex: Int, modelMap: ModelMap): String {
        val timeIndexFloor = bookRecordService.selectTimeIndexFloor(bookRecord.date, timeIndex, bookRecord.meetingRoomId)
        val timeIndexCeil = bookRecordService.selectTimeIndexCeil(bookRecord.date, timeIndex, bookRecord.meetingRoomId)
        val timeList = timeService.selectRangeByIndex(timeIndexFloor, timeIndexCeil)
        modelMap["timeList"] = timeList
        val meetingRoom = meetingRoomService.findById(bookRecord.meetingRoomId)
        val site = siteService.selectById(meetingRoom.siteId)
        modelMap["meetingRoom"] = meetingRoom;
        modelMap["site"] = site
        modelMap["date"] = bookRecord.date
        return "meeting_room_book"
    }

    @RequestMapping("book")
    fun book(bookRecord: BookRecord, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        val user = session.getAttribute("user") as User
        bookRecord.userId = user.id
        bookRecord.delete = false
        val bookRecords = bookRecordService.selectByIndexRange(bookRecord.startTimeIndex, bookRecord.endTimeIndex, bookRecord.date, bookRecord.meetingRoomId)
        if(bookRecords.isEmpty()) {
            bookRecordService.insert(bookRecord)
            redirectAttributes.addFlashAttribute("msg", "Success.")
        }else{
            redirectAttributes.addFlashAttribute("msg", "Time Error.")
        }
        return "redirect:/bookRecord/toBookRecord.do"
    }

    @RequestMapping("toBookRecord")
    fun toBookRecord(): String {
        return "book_record"
    }

    @RequestMapping("findBookRecordByPage")
    @ResponseBody
    fun findBookRecordByPage(session: HttpSession, modelMap: ModelMap, @RequestParam(defaultValue = "1") page: Int, @RequestParam(defaultValue = "10") limit: Int): Any {
        val user = session.getAttribute("user") as User
        val bookRecords: MutableList<MutableMap<String, Any?>> = bookRecordService.selectAllByUserId(user.id, page, limit)
        bookRecords.forEach {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            it["date"] = simpleDateFormat.format(it["date"])
            val timeDateFormat = SimpleDateFormat("HH:mm")
            it["start_time"] = timeDateFormat.format(it["start_time"])
            it["end_time"] = timeDateFormat.format(it["end_time"])
        }
        val count: Int = bookRecordService.selectCountByUserId(user.id)
        return mapOf("code" to 0, "msg" to "ok", "count" to count, "data" to bookRecords)
    }

    @RequestMapping(path = ["revoke"])
    fun revoke(id:Int, session: HttpSession, redirectAttributes :RedirectAttributes):String {
        val bookRecord:BookRecord = bookRecordService.selectById(id)
        val user = session.getAttribute("user") as User
        if(user.id == bookRecord.userId){
            val num:Int = bookRecordService.deleteById(id)
            if(num == 1) {
                redirectAttributes.addFlashAttribute("msg", "success")
            } else{
                redirectAttributes.addFlashAttribute("msg", "fail")
            }
        }else{
            redirectAttributes.addFlashAttribute("msg", "user error")
        }
        return "redirect:/bookRecord/toBookRecord.do"
    }
}
