package service.impl;

import bean.MeetingRoom;
import dao.MeetingRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.MeetingRoomService;

import java.util.List;

/**
 * @author NIce
 * @date 2018-04-25 11:12
 */
@Transactional
@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {
    private MeetingRoomDao meetingRoomDao;
    @Override
    public List<MeetingRoom> findBySiteId(Integer id) {
        return meetingRoomDao.findBySiteId(id);
    }

    @Override
    public MeetingRoom findById(int meetingRoomId) {
        return meetingRoomDao.findById(meetingRoomId);
    }

    @Autowired
    public void setMeetingRoomDao(MeetingRoomDao meetingRoomDao) {
        this.meetingRoomDao = meetingRoomDao;
    }
}
