package com.cognizant.iiht.fsd.casestudy.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.iiht.fsd.casestudy.dao.ProjectRepository;
import com.cognizant.iiht.fsd.casestudy.model.Project;
import com.cognizant.iiht.fsd.casestudy.model.ProjectDto;
import com.cognizant.iiht.fsd.casestudy.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public Project addProject(ProjectDto projectDto) {
		Project project = new Project();
		project.setProject(projectDto.getProject());
		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());
		project.setPriority(projectDto.getPriority());
		
		return projectRepository.save(project);
	}

	@Override
	public ProjectDto updateProject(ProjectDto projectDto) {
		System.out.println("projectDto: "+projectDto.getProjectId());
		Project project = findById(projectDto.getProjectId());
		System.out.println("project: "+project);
		if(project !=null) {
			BeanUtils.copyProperties(projectDto, project);
			System.out.println("project-inside: "+project);
			projectRepository.save(project);
		}
		return projectDto;
	}

	@Override
	public void deleteProject(long projectId) {
		projectRepository.deleteById(projectId);
	}

	@Override
	public List<Project> findProject(String searchkey) {
		List<Project> listOfProjects = new ArrayList<Project>();
		//projectRepository.findProjectBySearchKey(searchkey).iterator().forEachRemaining(listOfProjects :: add);
		
		return listOfProjects;
	}
	
	public Project findById(long projectId) {
		Optional<Project> Project = this.projectRepository.findById(projectId);
		return Project.isPresent() ? Project.get() : null;
	}
	
	@Override
	public List<Project> findAllProjects() {
		List<Project> listOfProjects = new ArrayList<Project>();
		projectRepository.findAll().iterator().forEachRemaining(listOfProjects :: add);
		return listOfProjects;
	}

}
