package com.cognizant.iiht.fsd.casestudy.service;

import java.util.List;
import com.cognizant.iiht.fsd.casestudy.model.User;
import com.cognizant.iiht.fsd.casestudy.model.UserDto;

public interface UserService {

	//CRDU operation
	public User addUser(UserDto userDto);
	public UserDto updateUser(UserDto userDto);
	public void deleteUser(long userId);
	public List<User> findUser(String searchkey);
	public List<User> findAllUsers();
}
