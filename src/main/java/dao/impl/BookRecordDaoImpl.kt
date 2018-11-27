package dao.impl

import bean.BookRecord
import dao.BookRecordDao
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author NIce
 * @date 2018-04-25 12:27
 */
@Repository
class BookRecordDaoImpl : BaseJdbcDaoSupport<BookRecord>(), BookRecordDao {
    override fun selectByIndexRange(startTimeIndex: Int, endTimeIndex: Int, date: Date, meetingRoomId: Int): MutableList<BookRecord> {
        val sql = "select * from book_record where date = ? and meeting_room_id = ? and (((start_time_index between ? and ? )or(end_time_index between ? and ?)))"
        return handler.query(sql, rowMapper, date, meetingRoomId, startTimeIndex, endTimeIndex, startTimeIndex, endTimeIndex)
    }

    override fun deleteById(bookRecordId: Int): Int {
        val sql = "delete from book_record where id = ?"
        return handler.update(sql, bookRecordId)
    }

    override fun selectById(bookRecordId: Int): BookRecord {
        return selectEqualAColumn("id", bookRecordId)
    }

    override fun selectAllByUserId(userId: Int, page: Int, limit: Int): MutableList<MutableMap<String, Any>> {
        val start = (page - 1) * limit
        val sql = "select book_record.*, startTime.start_time as start_time, endTime.end_time as end_time, site.name as site, meeting_room.name as meeting_room\n" +
                "from book_record, time as startTime, time as endTime, site, meeting_room\n" +
                "where book_record.start_time_index = startTime.`index` and book_record.end_time_index = endTime.`index` and book_record.meeting_room_id = meeting_room.id and meeting_room.site_id = site.id and book_record.user_id = ?\n" +
                "order by book_record.date desc" +
                " limit ?, ?"
        return handler.queryForList(sql, userId, start, limit)
    }

    override fun selectCountByUserId(userId: Int?): Int {
        return handler.queryForObject("select count(*) from book_record where user_id = ?", Int::class.java, userId)
    }

    override fun selectAllByUserId(userId: Int): MutableList<MutableMap<String, Any>> {
        val sql = "select book_record.*, startTime.start_time as start_time, endTime.end_time as end_time, site.name as site, meeting_room.name as meeting_room\n" +
                "from book_record, time as startTime, time as endTime, site, meeting_room\n" +
                "where book_record.start_time_index = startTime.`index` and book_record.end_time_index = endTime.`index` and book_record.meeting_room_id = meeting_room.id and meeting_room.site_id = site.id and book_record.user_id = ?\n" +
                "order by book_record.date desc"
        return handler.queryForList(sql, userId)
    }

    override fun insert(bookRecord: BookRecord): Int {
        val sql = "insert into book_record(user_id, meeting_room_id, date, start_time_index, end_time_index, is_delete) values(?,?,?,?,?,?)"
        val num = handler.update(sql, bookRecord.userId, bookRecord.meetingRoomId, bookRecord.date, bookRecord.startTimeIndex, bookRecord.endTimeIndex, bookRecord.delete)
        bookRecord.id = selectAutoIncrementPrimaryKey()
        return num
    }

    override fun selectTimeIndexFloor(date: Date, timeIndex: Int, meetingRoomId: Int): Int {
        val sql = "select max(end_time_index) from book_record where date = ? and end_time_index < ? and meeting_room_id = ?"
        var timeIndexFloor: Int? = handler.queryForObject(sql, Int::class.java, date, timeIndex, meetingRoomId)
        if (timeIndexFloor == null) {
            timeIndexFloor = handler.queryForObject("select min(`index`) from time", Int::class.java)
            if (timeIndexFloor == null) {
                timeIndexFloor = -1
            }
        } else {
            timeIndexFloor++
        }
        return timeIndexFloor
    }

    override fun selectTimeIndexCeil(date: Date, timeIndex: Int, meetingRoomId: Int): Int {
        val sql = "select min(start_time_index) from book_record where date = ? and start_time_index > ? and meeting_room_id = ?"
        var timeIndexCeil: Int? = handler.queryForObject(sql, Int::class.java, date, timeIndex, meetingRoomId)
        if (timeIndexCeil == null) {
            timeIndexCeil = handler.queryForObject("select max(`index`) from time", Int::class.java)
            if (timeIndexCeil == null) {
                timeIndexCeil = -1
            }
        } else {
            timeIndexCeil--
        }
        return timeIndexCeil
    }

    override fun selectDetailByDateAndRoomId(date: Date?, meetingRoomId: Int): MutableList<MutableMap<String, Any?>> {

        val sql = "select\n" +
                "  book_record.*,\n" +
                "  user.username\n" +
                "from book_record, user\n" +
                "where\n" +
                "  book_record.user_id = user.id and book_record.date = ? and book_record.meeting_room_id = ?"
        return handler.queryForList(sql, date, meetingRoomId)

    }

    override fun selectByDateAndRoomId(date: Date, meetingRoomId: Int): MutableList<BookRecord> {
        val sql = "select * from book_record where date = ? and meeting_room_id = ?"
        return handler.query(sql, rowMapper, date, meetingRoomId)
    }

    override fun createMapper(): RowMapper<BookRecord>? {
        return RowMapper { rs, rowNum ->
            return@RowMapper BookRecord().apply {
                id = rs.getInt("id")
                userId = rs.getInt("user_id")
                date = rs.getDate("date")
                meetingRoomId = rs.getInt("meeting_room_id")
                startTimeIndex = rs.getInt("start_time_index")
                endTimeIndex = rs.getInt("end_time_index")
                delete = rs.getBoolean("is_delete")
            }
        };
    }

    override fun getTableName(): String {
        return "book_record"
    }
}
