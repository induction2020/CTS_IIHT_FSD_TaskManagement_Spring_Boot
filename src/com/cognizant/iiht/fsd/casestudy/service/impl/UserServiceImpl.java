package com.cognizant.iiht.fsd.casestudy.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.iiht.fsd.casestudy.dao.UserRepository;
import com.cognizant.iiht.fsd.casestudy.model.User;
import com.cognizant.iiht.fsd.casestudy.model.UserDto;
import com.cognizant.iiht.fsd.casestudy.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User addUser(UserDto userDto) {
		User user = new User();
		user.setEmployeeId(userDto.getEmployeeId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		
		return userRepository.save(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		System.out.println("userDto: "+userDto.getUserId());
		User user = findById(userDto.getUserId());
		System.out.println("user: "+user);
		if(user !=null) {
			BeanUtils.copyProperties(userDto, user);
			System.out.println("user-inside: "+user);
			userRepository.save(user);
		}
		return userDto;
	}

	@Override
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<User> findUser(String searchkey) {
		List<User> listOfUsers = new ArrayList<User>();
		userRepository.findUserBySearchKey(searchkey).iterator().forEachRemaining(listOfUsers :: add);
		
		return listOfUsers;
	}
	
	public User findById(long userId) {
		Optional<User> User = this.userRepository.findById(userId);
		return User.isPresent() ? User.get() : null;
	}
	
	@Override
	public List<User> findAllUsers() {
		List<User> listOfUsers = new ArrayList<User>();
		userRepository.findAll().iterator().forEachRemaining(listOfUsers :: add);
		return listOfUsers;
	}

}
