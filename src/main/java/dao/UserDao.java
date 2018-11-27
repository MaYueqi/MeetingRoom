package dao;

import bean.User;

public interface UserDao {

    int deleteById(Integer id);

    int insert(User record);

    User selectById(Integer id);

    int updateById(User record);

    User selectByUsername(String username);
}