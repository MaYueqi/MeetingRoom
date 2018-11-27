package service;

import bean.Time;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author NIce
 * @date 2018-04-25 12:43
 */
public interface TimeService {
    List<Time> selectAll();

    List<Time> selectRange(Integer timeIdFloor, int timeIdCeil);

    Time selectById(int timeId);

    @NotNull
    List<Time> selectRangeByIndex(Integer timeIndexFloor, int timeIndexCeil);
}
