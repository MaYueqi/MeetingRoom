package dao;

import bean.Site;

import java.util.List;

public interface SiteDao {
    List<Site> selectAll();

    Site selectById(int siteId);
}