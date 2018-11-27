package dao.impl;

import bean.MeetingRoom;
import dao.MeetingRoomDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NIce
 * @date 2018-04-25 11:16
 */
@Repository
public class MeetingRoomDaoImpl extends BaseJdbcDaoSupport<MeetingRoom> implements MeetingRoomDao {

    @Override
    protected RowMapper<MeetingRoom> createMapper() {
        return (rs, rowNum)->{
            MeetingRoom meetingRoom = new MeetingRoom();
            meetingRoom.setId(rs.getInt("id"));
            meetingRoom.setName(rs.getString("name"));
            meetingRoom.setSiteId(rs.getInt("site_id"));
            return meetingRoom;
        };
    }

    @Override
    protected String getTableName() {
        return "meeting_room";
    }

    @Override
    public List<MeetingRoom> findBySiteId(Integer id) {
        String sql = "select * from meeting_room where site_id = ?";
        return handler.query(sql, rowMapper, id);
    }

    @Override
    public MeetingRoom findById(int meetingRoomId) {
        return selectEqualAColumn("id", meetingRoomId);
    }
}
