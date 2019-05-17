package com.cognizant.iiht.fsd.casestudy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.iiht.fsd.casestudy.dao.TaskRepository;
import com.cognizant.iiht.fsd.casestudy.dao.UserRepository;
import com.cognizant.iiht.fsd.casestudy.model.ParentTaskDo;
import com.cognizant.iiht.fsd.casestudy.model.Project;
import com.cognizant.iiht.fsd.casestudy.model.Task;
import com.cognizant.iiht.fsd.casestudy.model.TaskDto;
import com.cognizant.iiht.fsd.casestudy.model.User;
import com.cognizant.iiht.fsd.casestudy.service.ProjectService;
import com.cognizant.iiht.fsd.casestudy.service.TaskService;
import com.cognizant.iiht.fsd.casestudy.service.UserService;


@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepository taskrepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@Override
	public Task addTask(TaskDto taskDto) {
		Task taskDo = new Task();
		taskDo.setTaskId(taskDto.getTaskId());
		taskDo.setTask(taskDto.getTask());
		taskDo.setStartDate(taskDto.getStartDate());
		taskDo.setEndDate(taskDto.getEndDate());
		taskDo.setPriority(taskDto.getPriority());
		
		if(taskDto.getParentId()!=0 ) {
			Task taskDoTemp = findById(taskDto.getParentId());
			
			ParentTaskDo parentTask = new ParentTaskDo();
			
			parentTask.setParentId(taskDoTemp.getTaskId() );
			parentTask.setParentTask( taskDoTemp.getTask() );
			
			taskDo.setParentTaskDo(parentTask);
			parentTask.setTaskdo(taskDo);
		}
		
		if(taskDto.getUserId()!=0){
			User user = userService.findById(taskDto.getUserId());
			List<Task> taskListTemp =  null;
			if(user.getTask() ==null) {
				taskListTemp = new ArrayList<>();
			}else {
				taskListTemp = user.getTask() ;
			}
			taskListTemp.add(taskDo);
			
			user.setTask(taskListTemp);
			taskDo.setUser(user);
		}
		
		if(taskDto.getProjectId() !=0 ) {
			Project project = projectService.findById( taskDto.getProjectId());
			List<Task> taskListTemp =  null;
			if(project.getTask() ==null) {
				taskListTemp = new ArrayList<>();
			}else {
				taskListTemp = project.getTask() ;
			}
			taskListTemp.add(taskDo);
			
			project.setTask( taskListTemp );
			taskDo.setProject(project);
		}
		
		return taskrepository.save(taskDo);
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto) {
		Task taskDo = findById(taskDto.getTaskId());
		if(taskDo!=null){
			BeanUtils.copyProperties(taskDto, taskDo);
			taskrepository.save(taskDo);
		}
		
		return taskDto;
		
	}

	@Override
	public void deleteTask(long taskId) {
		taskrepository.deleteById(taskId);
		
	}

	@Override
	public Task findTask(long taskId) {
		return findById(taskId);
	}
	
	public Task findById(long taskId) {
		
		Optional<Task> taskDo = taskrepository.findById(taskId);
		return taskDo.isPresent() ? taskDo.get() : null;
	}

	@Override
	public TaskDto updateParentTask(TaskDto taskDto) {
		return null;
	}

	
	
	@Override
	public List<Task> findTasksByProject(String projectName) {
		System.out.println("findTasksByProject");
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findTasksByProject(projectName).iterator().forEachRemaining(listOfTasks :: add);
		System.out.println("findTasksByProject Ends");
		return listOfTasks;
	}
	
	@Override
	public List<Task> findAllTasks() {
		System.out.println("findAllTasks");
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findAll().iterator().forEachRemaining(listOfTasks :: add);
		System.out.println("findAllTasks Ends");
		return listOfTasks;
	}

	@Override
	public Task findTaskByName(String taskName) {
		Optional<Task> optionalTasks = taskrepository.findTaskByName(taskName);
		Task taskDoTemp = optionalTasks.isPresent() ? optionalTasks.get() : null;
		return taskDoTemp;
	}
	
	@Override
	public List<Task> findTaskByParentTask(String taskName) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findTaskByParentTask(taskName).iterator().forEachRemaining(listOfTasks :: add);
		 		
		return listOfTasks;
	}

	@Override
	public List<Task> findTaskByPriority(int lowPriority, int highPriority) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findTaskByPriority( lowPriority, highPriority).iterator().forEachRemaining(listOfTasks :: add);
		return listOfTasks;
	}

	@Override
	public List<Task> findTaskByDates(String startDate, String endDate) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findTaskByDates(startDate, endDate).iterator().forEachRemaining(listOfTasks :: add);
		return listOfTasks;
	}

	@Override
	public List<Task> searchTask(String taskName) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.searchTask(taskName).iterator().forEachRemaining(listOfTasks :: add);
		return listOfTasks;
	}
	

}
