package com.xl.smbms.service;

import java.util.List;
import java.util.Map;

import com.xl.smbms.entity.Role;
import com.xl.smbms.entity.User;

public interface UserService {
	public User login(String code);

	public int total(Map<String, Object> map);

	public List<User> search(Map<String, Object> map);

	public int check(String userCode);

	public int insert(User user);

	public int delete(String id);

	public User getUserById(String id);

	public int update(User user);

	public List<Role> getAllRoles();
}
