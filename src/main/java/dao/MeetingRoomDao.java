package dao;

import bean.MeetingRoom;

import java.util.List;

public interface MeetingRoomDao {

    List<MeetingRoom> findBySiteId(Integer id);

    MeetingRoom findById(int meetingRoomId);
}