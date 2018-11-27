package dao.impl;

import bean.Site;
import dao.SiteDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NIce
 * @date 2018-04-24 11:20
 */

@Repository
public class SiteDaoImpl extends BaseJdbcDaoSupport<Site> implements SiteDao {
    @Override
    protected RowMapper<Site> createMapper() {
        return (rs, rowNum)->{
            Site site = new Site();
            site.setId(rs.getInt("id"));
            site.setName(rs.getString("name"));
            return site;
        };
    }

    @Override
    protected String getTableName() {
        return "site";
    }

    @Override
    public List<Site> selectAll() {
        return selectAllRecord();
    }

    @Override
    public Site selectById(int siteId) {
        return selectEqualAColumn("id", siteId);
    }
}
