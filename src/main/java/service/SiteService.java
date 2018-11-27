package service;

import bean.Site;

import java.util.List;

/**
 * @author NIce
 * @date 2018-04-24 11:17
 */
public interface SiteService {
    List<Site> selectAll();
    Site selectById(int siteId);
}
