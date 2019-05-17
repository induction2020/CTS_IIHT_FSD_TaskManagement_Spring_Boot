package com.cognizant.iiht.fsd.casestudy.service;

import java.util.List;
import com.cognizant.iiht.fsd.casestudy.model.Project;
import com.cognizant.iiht.fsd.casestudy.model.ProjectDto;

public interface ProjectService {

	//CRDU operation
	public Project addProject(ProjectDto ProjectDto);
	public ProjectDto updateProject(ProjectDto ProjectDto);
	public void deleteProject(long ProjectId);
	public List<Project> findProject(String searchkey);
	public List<Project> findAllProjects();
	public Project findById(long projectId);
}
