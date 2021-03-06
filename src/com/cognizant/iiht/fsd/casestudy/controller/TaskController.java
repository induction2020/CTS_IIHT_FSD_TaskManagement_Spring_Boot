package com.cognizant.iiht.fsd.casestudy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.iiht.fsd.casestudy.model.ApiResponse;
import com.cognizant.iiht.fsd.casestudy.model.ParentTaskDo;
import com.cognizant.iiht.fsd.casestudy.model.Task;
import com.cognizant.iiht.fsd.casestudy.model.TaskDto;
import com.cognizant.iiht.fsd.casestudy.model.TaskSearch;
import com.cognizant.iiht.fsd.casestudy.service.TaskService;

@CrossOrigin(origins = "*", maxAge = 3600,  allowedHeaders = "*")
@RestController
@EnableAutoConfiguration
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping(value ="tasks123")
	public List<Task> getTaskList(){
		TaskDto taskDto = new TaskDto();
		taskDto.setTask("Thiruappathi");
		taskDto.setStartDate("Date1");
		taskDto.setEndDate("Date2");
		taskDto.setPriority(10);
		taskDto.setParentId(1);
		taskDto.setUserId(1);
		taskDto.setProjectId(1);
		
		//taskService.addTask(taskDto);
		
		List<Task> listOfTasks = taskService.findAllTasks();
		
		//taskService.deleteTask(4);	
		
		return listOfTasks;
		
	}
	
			
	/**
	 * 
	 * Implementation of Rest Services
	 * 
	 */
	
	
	//Retrieve All Tasks
	 @GetMapping
	 public List<TaskDto> listTasks(){
		 System.out.println("listTasks");
		 List<Task> listOfTasks = taskService.findAllTasks();
		 //List<Task> listOfTasks = taskService.findTasksByProject();
		 List<TaskDto> listOfTasksDto = new ArrayList<>(); 
		 TaskDto taskDto = null;
		 for(int i=0; i<listOfTasks.size(); i++){
			 taskDto = new TaskDto();
			 System.out.println("listOfTasks.get(i): "+ listOfTasks.get(i));
			
				
			 BeanUtils.copyProperties(listOfTasks.get(i), taskDto);
			 if( listOfTasks.get(i).getParentTaskDo() != null ){
				 ParentTaskDo parent = listOfTasks.get(i).getParentTaskDo();
				 System.out.println("parent:"+parent.toString());
				 taskDto.setParentId( listOfTasks.get(i).getParentTaskDo().getParentId() );
				 taskDto.setParentName( listOfTasks.get(i).getParentTaskDo().getParentTask());
				 System.out.println("taskDto: "+taskDto.toString()); 
			 }
			 
			 if( listOfTasks.get(i).getUser() != null ){
				 long userId = listOfTasks.get(i).getUser().getUserId();
				 taskDto.setUserId(userId); 
			 }
			 
			 
			 
			 listOfTasksDto.add( taskDto );
		 }
		return listOfTasksDto;
	 }
	 
	 		//Retrieve All Tasks
		 @GetMapping("/project/{projectName}")
		 public List<TaskDto> findTasksByProject(@PathVariable String projectName){
			 System.out.println("listTasks");
			 //List<Task> listOfTasks = taskService.findAllTasks();
			 List<Task> listOfTasks = taskService.findTasksByProject(projectName);
			 List<TaskDto> listOfTasksDto = new ArrayList<>(); 
			 TaskDto taskDto = null;
			 for(int i=0; i<listOfTasks.size(); i++){
				 taskDto = new TaskDto();
				 System.out.println("listOfTasks.get(i): "+ listOfTasks.get(i));
				
					
				 BeanUtils.copyProperties(listOfTasks.get(i), taskDto);
				 if( listOfTasks.get(i).getParentTaskDo() != null ){
					 ParentTaskDo parent = listOfTasks.get(i).getParentTaskDo();
					 System.out.println("parent:"+parent.toString());
					 taskDto.setParentId( listOfTasks.get(i).getParentTaskDo().getParentId() );
					 taskDto.setParentName( listOfTasks.get(i).getParentTaskDo().getParentTask());
					 System.out.println("taskDto: "+taskDto.toString()); 
				 }
				 
				 if( listOfTasks.get(i).getUser() != null ){
					 long userId = listOfTasks.get(i).getUser().getUserId();
					 taskDto.setUserId(userId); 
				 }
				 
				 
				 
				 listOfTasksDto.add( taskDto );
			 }
			return listOfTasksDto;
		 }
		 
		 
	 //Retrieve One Task
	 @GetMapping("/{id}")
     public TaskDto getTask(@PathVariable int id){
		 Task task = taskService.findTask(id);
		 TaskDto taskDto = new TaskDto();
		 BeanUtils.copyProperties(task, taskDto);
		 if(task.getParentTaskDo()!=null){
			 //taskDto.setParentName(task.getParentTaskDo().getParentTask());	 
		 }else{
			// taskDto.setParentName("");
		 }
		 
        return taskDto;
     }
	
	// Add Task
	@PostMapping
	public void saveTask(@RequestBody TaskDto taskDto){
		taskService.addTask(taskDto);
	   // return new ApiResponse<Task>( HttpStatus.OK.value() , "Task saved successfully.", taskService.addTask(taskDto));
	}
	
	
	 //Update Task
	 @PutMapping
     public TaskDto update(@RequestBody TaskDto taskDto) {
         return taskService.updateTask(taskDto);
     }
	 
	 
	 //Delete Task
	 @DeleteMapping("/{id}")
	 public ApiResponse<Void> delete(@PathVariable int id) {
		 taskService.deleteTask(id);
	    return new ApiResponse<>(HttpStatus.OK.value(), "Task deleted successfully.", null);
	 }
			
	//Search
	 @RequestMapping(value="/search", method= RequestMethod.POST)
	 public List<TaskDto> searchTask(@RequestBody TaskSearch tasksearch){
		 System.out.println("listTasks");
		 //List<Task> listOfTasks = taskService.findAllTasks();
		 List<Task> listOfTasks = taskService.findTasksByProject(tasksearch.getTask());
		 List<TaskDto> listOfTasksDto = new ArrayList<>(); 
		 TaskDto taskDto = null;
		 for(int i=0; i<listOfTasks.size(); i++){
			 taskDto = new TaskDto();
			 System.out.println("listOfTasks.get(i): "+ listOfTasks.get(i));
			
				
			 BeanUtils.copyProperties(listOfTasks.get(i), taskDto);
			 if( listOfTasks.get(i).getParentTaskDo() != null ){
				 ParentTaskDo parent = listOfTasks.get(i).getParentTaskDo();
				 System.out.println("parent:"+parent.toString());
				 taskDto.setParentId( listOfTasks.get(i).getParentTaskDo().getParentId() );
				 taskDto.setParentName( listOfTasks.get(i).getParentTaskDo().getParentTask());
				 System.out.println("taskDto: "+taskDto.toString()); 
			 }
			 
			 if( listOfTasks.get(i).getUser() != null ){
				 long userId = listOfTasks.get(i).getUser().getUserId();
				 taskDto.setUserId(userId); 
			 }
			 
			 
			 
			 listOfTasksDto.add( taskDto );
		 }
		return listOfTasksDto;
		
	 }

	 //Other methods
	 
	 //Search Task By Name
	 @RequestMapping(value="/searchByTask", method= RequestMethod.GET )
	 public Task searchByTask( ){
		 System.out.println("...searchByTask...");
		return taskService.findTaskByName("hhg");
		
		//Example: http://localhost:8098/tasks/searchByTask/Thiruppathi%20Madhu%20Upd
	 }
	
	//Search Task By Parent Task
	 @RequestMapping(value="/searchByParentTask/{taskName}")
	 public ApiResponse<List<Task>> searchTaskByParentTask(@PathVariable("taskName") String taskName ){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tasks fetched successfully.", taskService.findTaskByParentTask(taskName) );
		
		//Example: http://localhost:8098/tasks/searchByParentTask/Thiruppathi%20Madhu
	 }
	 
	 
	//Search Task By Priority
	 @RequestMapping(value="/searchTaskByPriority/{lowPriority}/{highPriority}")
	 public ApiResponse<List<Task>> searchTaskByPriority(@PathVariable("lowPriority") int lowPriority,
			 					@PathVariable("highPriority") int highPriority  ){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tasks fetched successfully.", taskService.findTaskByPriority(lowPriority, highPriority) );
		
		//Example: http://localhost:8098/tasks/searchByParentTask/Thiruppathi%20Madhu
	 }
	 
	//Search Task By Date
	 @RequestMapping(value="/searchTaskByDates/{startDate}/{endDate}")
	 public ApiResponse<List<Task>> searchTaskByDates(@PathVariable("startDate") String startDate,
			 					@PathVariable("endDate") String endDate  ){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tasks fetched successfully.", taskService.findTaskByDates(startDate, endDate) );
		
		//Example: http://localhost:8098/tasks/searchByParentTask/Thiruppathi%20Madhu
	 }

}
