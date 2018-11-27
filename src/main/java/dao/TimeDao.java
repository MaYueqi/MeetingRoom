package dao;

import bean.Time;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TimeDao {

    int deleteById(Integer id);

    int insert(Time record);

    Time selectById(Integer id);

    int updateById(Time record);

    List<Time> selectAllTime();

    List<Time> selectRange(int timeIdFloor, int timeIdCeil);

    @NotNull
    List<Time> selectRangeByIndex(int timeIndexFloor, int timeIndexCeil);
}