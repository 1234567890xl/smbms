package com.xl.smbms.dao;

import com.xl.smbms.entity.Role;
import com.xl.smbms.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User login(String code);

    List<User> search(Map<String, Object> map);

    int total(Map<String, Object> map);

    List<Role> getAllRoles();

    int check(String userCode);

    int insert(User user);

    int delete(String id);

    User getUserById(String id);

    int update(User user);
}
