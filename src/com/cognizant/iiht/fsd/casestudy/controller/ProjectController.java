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
import com.cognizant.iiht.fsd.casestudy.model.Project;
import com.cognizant.iiht.fsd.casestudy.model.ProjectDto;
import com.cognizant.iiht.fsd.casestudy.service.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600,  allowedHeaders = "*")
@RestController
@EnableAutoConfiguration
@RequestMapping("/projects")
public class ProjectController {

	/**
	 * 
	 * Implementation of Rest Services
	 * 
	 */
	@Autowired
	ProjectService projectService;
	
	//Retrieve All Tasks
	 @GetMapping
	 public List<Project> listProjects(){
		 System.out.println("listTasks");
		 
		return projectService.findAllProjects();
	 }
	 
	 @GetMapping(value ="project123")
	 public ApiResponse<Task> saveProject(){
		 
		  ProjectDto projectDto = new ProjectDto();
		  projectDto.setProject("Thiru Project");
		  projectDto.setStartDate("12-05-2019");
		  projectDto.setEndDate("12-05-2019");
		  projectDto.setPriority("12");
		  return new ApiResponse<Task>( HttpStatus.OK.value() , "Project saved successfully.", projectService.addProject(projectDto));
			
	 }
	 
	//Retrieve All Tasks
	// Add Task
		@PostMapping
		public ApiResponse<Task> save(@RequestBody ProjectDto projectDto){
		    return new ApiResponse<Task>( HttpStatus.OK.value() , "Project saved successfully.", projectService.addProject(projectDto));
		}
		
		
		 //Update Task
		 @PutMapping
	     public ProjectDto update(@RequestBody ProjectDto ProjectDto) {
	         return projectService.updateProject(ProjectDto);
	     }
		 
		 
		 //Delete Task
		 @DeleteMapping("/{id}")
		 public ApiResponse<Void> delete(@PathVariable int id) {
			 projectService.deleteProject(id);
		    return new ApiResponse<>(HttpStatus.OK.value(), "Project deleted successfully.", null);
		 }
				
		//Search
		 @RequestMapping(value="/search", method= RequestMethod.POST)
		 public List<Project> searchTask(@RequestBody Project project){
			 System.out.println("search method");
			
			 return projectService.findProject("search");
			
		 }
	 
}
