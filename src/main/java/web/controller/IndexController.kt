package web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import service.BookRecordService
import service.MeetingRoomService
import service.SiteService
import service.TimeService
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.collections.HashMap

/**
 * @author NIce
 * @date 2018-04-24 19:56
 */
@Controller
class IndexController {
    @Autowired
    lateinit var bookRecordService: BookRecordService

    @Autowired
    lateinit var timeService: TimeService

    @Autowired
    lateinit var siteService: SiteService

    @Autowired
    lateinit var meetingRoomService: MeetingRoomService

    @RequestMapping(path = ["index"], method = [RequestMethod.GET])
    fun toIndex(modelMap: ModelMap): String {
        val nowDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val timeList = timeService.selectAll()
        val siteList = siteService.selectAll()
        val meetingRoomGroupBySiteIdList = siteList.map {
            meetingRoomService.findBySiteId(it.id)
        }
        val mapListList = meetingRoomGroupBySiteIdList.map { meetingRoomList ->
            meetingRoomList.map { meetingRoom ->
                val details = bookRecordService.selectDetailByDateAndRoomId(SimpleDateFormat("yyyy-MM-dd").parse(nowDate), meetingRoom.id)
                val map = HashMap<Any?, Any?>(details.size)
                details.forEach { record ->
                    val startTimeId = record["start_time_index"] as Int
                    val endTimeId = record["end_time_index"] as Int
                    for (i in startTimeId..endTimeId)
                        map.put(i, record)
                }
                map
            }
        }
        modelMap.put("nowDate", nowDate)
        modelMap.put("timeList", timeList)
        modelMap.put("siteList", siteList)
        modelMap.put("meetingRoomGroupBySiteIdList", meetingRoomGroupBySiteIdList)
        modelMap.put("mapListList", mapListList)
        println(mapListList)
        return "index"
    }

    @RequestMapping("toLogin")
    fun toLogin(): String {
        return "login"
    }

    @RequestMapping("toRegister")
    fun toRegister(): String {
        return "user_register"
    }

    @RequestMapping("toError")
    fun toError(): String {
//        throw RuntimeException()
        return "error"
    }
}
