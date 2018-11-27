package service.impl;

import bean.Site;
import dao.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SiteService;

import java.util.List;

/**
 * @author NIce
 * @date 2018-04-24 11:18
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {
    private SiteDao siteDao;

    @Override
    public List<Site> selectAll() {
        return siteDao.selectAll();
    }

    @Override
    public Site selectById(int siteId) {
        return siteDao.selectById(siteId);
    }

    @Autowired
    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }
}
