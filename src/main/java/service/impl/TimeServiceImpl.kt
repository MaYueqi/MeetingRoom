package service.impl

import bean.Time
import dao.TimeDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import service.TimeService

/**
 * @author NIce
 * @date 2018-04-25 12:43
 */
@Service
class TimeServiceImpl : TimeService {
    override fun selectRangeByIndex(timeIndexFloor: Int, timeIndexCeil: Int): MutableList<Time> {
        return timeDao.selectRangeByIndex(timeIndexFloor, timeIndexCeil)
    }

    override fun selectById(timeId: Int): Time {
        return timeDao.selectById(timeId)
    }

    override fun selectRange(timeIdFloor: Int, timeIdCeil: Int): MutableList<Time> {
        return timeDao.selectRange(timeIdFloor, timeIdCeil)
    }

    override fun selectAll(): MutableList<Time> {
        return timeDao.selectAllTime()
    }

    @Autowired
    lateinit var timeDao: TimeDao
}
