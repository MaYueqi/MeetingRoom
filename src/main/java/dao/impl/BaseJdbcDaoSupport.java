package dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author NIce
 * @date 2018-04-24 07:58
 */
public abstract class BaseJdbcDaoSupport<T> extends JdbcDaoSupport {
    protected JdbcTemplate handler;

    protected RowMapper<T> rowMapper;

    protected String tableName;

    @Autowired
    public void forSetDataSource(DataSource dataSource) {
        this.setDataSource(dataSource);
        handler = this.getJdbcTemplate();
        rowMapper = createMapper();
        tableName = getTableName();
    }


    protected abstract RowMapper<T> createMapper();

    protected abstract String getTableName();

    protected Integer selectAutoIncrementPrimaryKey() {
        String sql = "select last_insert_id();";
        return handler.queryForObject(sql, Integer.class);
    }

    protected T selectEqualAColumn(String column, Object id) {
        String sql = new StringBuilder(30).append("select * from ").append(tableName).append(" where ").append(column).append(" =  ?").toString();
        List<T> list = handler.query(sql, rowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    protected Integer deleteByPrimaryKey(String column, Object id) {
        String sql = new StringBuilder(30).append("delete from ").append(tableName).append(" where ").append(column).append( " = ?").toString();
        return handler.update(sql, id);
    }

    protected List<T> selectAllRecord(){
        String sql = new StringBuilder(30).append("select * from ").append(tableName).toString();
        return handler.query(sql, rowMapper);
    }
}
