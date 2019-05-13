package com.cognizant.iiht.fsd.casestudy.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.iiht.fsd.casestudy.model.ApiResponse;
import com.cognizant.iiht.fsd.casestudy.model.Task;
import com.cognizant.iiht.fsd.casestudy.model.User;
import com.cognizant.iiht.fsd.casestudy.model.UserDto;
import com.cognizant.iiht.fsd.casestudy.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600,  allowedHeaders = "*")
@RestController
@EnableAutoConfiguration
@RequestMapping("/users")
public class UserController {

	/**
	 * 
	 * Implementation of Rest Services
	 * 
	 */
	@Autowired
	UserService userService;
	
	//Retrieve All Tasks
	 @GetMapping
	 public List<User> listUsers(){
		 System.out.println("listTasks");
		 
		return userService.findAllUsers();
	 }
	 
	 @GetMapping(value ="users123")
	 public ApiResponse<Task> saveUser(){
		 
		  UserDto userDto = new UserDto("Thiru","Madhu","123");
		  return new ApiResponse<Task>( HttpStatus.OK.value() , "User saved successfully.", userService.addUser(userDto));
			
	 }
	 
	//Retrieve All Tasks
	// Add Task
		@PostMapping
		public ApiResponse<Task> save(@RequestBody UserDto userDto){
		    return new ApiResponse<Task>( HttpStatus.OK.value() , "User saved successfully.", userService.addUser(userDto));
		}
		
		
		 //Update Task
		 @PutMapping
	     public UserDto update(@RequestBody UserDto UserDto) {
	         return userService.updateUser(UserDto);
	     }
		 
		 
		 //Delete Task
		 @DeleteMapping("/{id}")
		 public ApiResponse<Void> delete(@PathVariable int id) {
			 userService.deleteUser(id);
		    return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
		 }
				
		//Search
		 @RequestMapping(value="/search", method= RequestMethod.POST)
		 public List<User> searchTask(@RequestBody User user){
			 System.out.println("search method");
			
			 return userService.findUser(user.getFirstName());
			
		 }
	 
}
