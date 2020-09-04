package com.xl.smbms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xl.smbms.dao.UserMapper;
import com.xl.smbms.entity.Role;
import com.xl.smbms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper um;
	public User login(String code) {
		// TODO Auto-generated method stub
		return um.login(code);
	}
	public List<User> search(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return um.search(map);
	}


	public List<Role> getAllRoles(){
		return um.getAllRoles();

	}
	public int total(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return um.total(map);
	}
	public int check(String userCode) {
		// TODO Auto-generated method stub
		return um.check(userCode);
	}
	public int insert(User user) {
		// TODO Auto-generated method stub
		return um.insert(user);

	}
	public int delete(String id) {
		// TODO Auto-generated method stub
		return um.delete(id);
	}
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return um.getUserById(id);
	}
	public int update(User user) {
		// TODO Auto-generated method stub
		return um.update(user);
	}
}
