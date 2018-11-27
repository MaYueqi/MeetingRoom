package service.impl

import bean.BookRecord
import dao.BookRecordDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import service.BookRecordService
import java.util.*

/**
 * @author NIce
 * @date 2018-04-25 12:25
 */
@Service
@Transactional
class BookRecordServiceImpl : BookRecordService {
    override fun selectByIndexRange(startTimeIndex: Int, endTimeIndex: Int, date: Date, meetingRoomId: Int): MutableList<BookRecord> {
        return bookRecordDao.selectByIndexRange(startTimeIndex, endTimeIndex, date, meetingRoomId)
    }

    override fun deleteById(bookRecordId: Int): Int {
        return bookRecordDao.deleteById(bookRecordId)
    }

    override fun selectById(bookRecordId: Int): BookRecord {
        return bookRecordDao.selectById(bookRecordId)
    }

    override fun selectAllByUserId(userId: Int, page: Int, limit: Int): MutableList<MutableMap<String, Any>> {
        return bookRecordDao.selectAllByUserId(userId, page, limit)
    }

    override fun selectCountByUserId(userId: Int?): Int {
        return bookRecordDao.selectCountByUserId(userId)
    }

    override fun selectAllByUserId(userId: Int?): MutableList<MutableMap<String, Any>> {
        return bookRecordDao.selectAllByUserId(userId)
    }

    override fun insert(bookRecord: BookRecord): Int {
        return bookRecordDao.insert(bookRecord)
    }


    override fun selectTimeIndexFloor(date: Date, timeIndex: Int, meetingRoomId: Int): Int {
        return bookRecordDao.selectTimeIndexFloor(date, timeIndex, meetingRoomId)
    }

    override fun selectTimeIndexCeil(date: Date, timeIndex: Int, meetingRoomId: Int): Int {
        return bookRecordDao.selectTimeIndexCeil(date, timeIndex, meetingRoomId)
    }

    override fun selectDetailByDateAndRoomId(date: Date?, meetingRoomId: Int): MutableList<MutableMap<String, Any>> {
        return bookRecordDao.selectDetailByDateAndRoomId(date, meetingRoomId)
    }

    override fun selectByDateAndRoomId(date: Date, meetingRoomId: Int): MutableList<BookRecord>? {
        return bookRecordDao.selectByDateAndRoomId(date, meetingRoomId)
    }

    @Autowired
    lateinit var bookRecordDao: BookRecordDao
}
