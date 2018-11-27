package service;

import bean.User;

public interface UserService {

    int deleteById(Integer id);

    int insert(User record);

    User selectById(Integer id);

    int updateById(User record);

    User selectByUsername(String username);
}