package dao;

import bean.BookRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BookRecordDao {
    List<BookRecord> selectByDateAndRoomId(Date date, int meetingRoomId);

    List<Map<String, Object>> selectDetailByDateAndRoomId(Date date, int meetingRoomId);

    int selectTimeIndexFloor(Date date, int timeIndex, int meetingRoomId);

    int selectTimeIndexCeil(Date date, int timeIndex, int meetingRoomId);

    int insert( BookRecord bookRecord);

    List<Map<String, Object>> selectAllByUserId(Integer userId);

    List<Map<String, Object>> selectAllByUserId(Integer userId, Integer page, Integer limit);

    int selectCountByUserId(Integer userId);

    BookRecord selectById(int bookRecordId);

    int deleteById(int bookRecordId);

    List<BookRecord> selectByIndexRange(int startTimeIndex, int endTimeIndex, Date date, Integer meetingRoomId);
}