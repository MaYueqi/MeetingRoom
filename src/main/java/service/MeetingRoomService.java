package service;

import bean.MeetingRoom;

import java.util.List;

/**
 * @author NIce
 * @date 2018-04-25 11:10
 */
public interface MeetingRoomService {
    List<MeetingRoom> findBySiteId(Integer id);
    MeetingRoom findById(int meetingRoomId);
}
