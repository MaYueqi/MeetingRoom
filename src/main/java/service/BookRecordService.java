package service;

import bean.BookRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author NIce
 * @date 2018-04-25 12:25
 */
public interface BookRecordService {
    List<BookRecord> selectByDateAndRoomId(Date date, int meetingRoomId);

    List<Map<String, Object>> selectDetailByDateAndRoomId(Date date, int meetingRoomId);

    Integer selectTimeIndexFloor(Date date, Integer timeIndex, Integer meetingRoomId);

    int selectTimeIndexCeil(Date date, Integer timeIndex, Integer meetingRoomId);

    Integer insert(BookRecord bookRecord);

    List<Map<String, Object>> selectAllByUserId(Integer userId);

    List<Map<String, Object>> selectAllByUserId(Integer userId, Integer page, Integer limit);

    int selectCountByUserId(Integer userId);

    BookRecord selectById(int bookRecordId);

    int deleteById(int bookRecordId);

    List<BookRecord> selectByIndexRange( Integer startTimeIndex, Integer endTimeIndex, Date date, Integer meetingRoomId);
}
