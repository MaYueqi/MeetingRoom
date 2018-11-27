package dao.impl;

import bean.Time;
import dao.TimeDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author NIce
 * @date 2018-04-24 09:11
 */

@Repository
public class TimeDaoImpl extends BaseJdbcDaoSupport<Time> implements TimeDao {
    @Override
    public int deleteById(Integer id) {
        return deleteByPrimaryKey("id", id);
    }

    @Override
    public int insert(Time record) {
        String sql = "insert into time(start_time, end_time) values(?,?)";
        int count = handler.update(sql, record.getStartTime(), record.getEndTime());
        if (count == 1) {
            record.setId(selectAutoIncrementPrimaryKey());
        }
        return count;
    }

    @Override
    public Time selectById(Integer id) {
        return selectEqualAColumn("id", id);
    }

    @Override
    public int updateById(Time record) {
        String sql = "update time set start_time = ?, end_time = ? where id = ?";
        return handler.update(sql, record.getStartTime(), record.getEndTime());
    }

    @Override
    public List<Time> selectAllTime() {
        List<Time> timeList = selectAllRecord();
        timeList.sort((time1, time2) -> time1.getIndex() - time2.getIndex());
        return timeList;
    }

    @Override
    public List<Time> selectRange(int timeIdFloor, int timeIdCeil) {
        String sql = "select * from time where id between ? and ?";
        List<Time> timeList = handler.query(sql, rowMapper, timeIdFloor, timeIdCeil);
        timeList.sort((time1, time2) -> time1.getIndex() - time2.getIndex());
        return timeList;
    }

    @Override
    public List<Time> selectRangeByIndex(int timeIndexFloor, int timeIndexCeil) {
        String sql = "select * from time where `index` between ? and ?";
        List<Time> timeList = handler.query(sql, rowMapper, timeIndexFloor, timeIndexCeil);
        timeList.sort((time1, time2) -> time1.getIndex() - time2.getIndex());
        return timeList;
    }

    @Override
    protected String getTableName() {
        return "time";
    }

    @Override
    public RowMapper<Time> createMapper() {
        return (rs, rowNum) -> {
            Time time = new Time();
            time.setId(rs.getInt("id"));
            time.setStartTime(rs.getTime("start_time"));
            time.setEndTime(rs.getTime("end_time"));
            time.setIndex(rs.getInt("index"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            return time;
        };
    }
}
