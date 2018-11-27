package dao.impl;

import bean.User;
import dao.UserDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author NIce
 * @date 2018-04-24 07:49
 */

@Repository
public class UserDaoImpl extends BaseJdbcDaoSupport<User> implements UserDao {

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from user where id = ?";
        return handler.update(sql, id);
    }

    @Override
    public int insert(User record) {
        String sql = "insert into user(username, password, is_expired, site_id) values (?,?,?,?)";
        int count = handler.update(sql, record.getUsername(), record.getPassword(), record.getExpired(), record.getSiteId());
        if (count == 1) {
            record.setId(this.selectAutoIncrementPrimaryKey());
        }
        return count;
    }

    @Override
    public User selectById(Integer id) {
        String sql = "select * from user where id = ?";
        return handler.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int updateById(User record) {
        String sql = "update user set username = ?, password = ?, is_expired = ?, site_id = ? where id = ?";
        return handler.update(sql, record.getUsername(), record.getPassword(), record.getExpired(), record.getSiteId(), record.getId());
    }

    @Override
    public User selectByUsername(String username) {
        return selectEqualAColumn("username", username);
    }

    @Override
    @SuppressWarnings("all")
    public RowMapper<User> createMapper() {

        // SAM Lambda实现 JDK 8
        RowMapper<User> rowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setExpired(rs.getBoolean("is_expired"));
            user.setSiteId(rs.getInt("site_id"));
            return user;
        };

        /*
            SAM 匿名对象实现
        rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setExpired(rs.getBoolean("is_expired"));
                user.setSiteId(rs.getInt("site_id"));
                return user;
            }
        };*/
        return rowMapper;
    }

    @Override
    protected String getTableName() {
        return "user";
    }
}
