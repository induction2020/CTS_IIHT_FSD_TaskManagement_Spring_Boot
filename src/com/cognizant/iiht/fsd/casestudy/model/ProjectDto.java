package com.cognizant.iiht.fsd.casestudy.model;

public class ProjectDto {

	private long projectId;
	private String project;
	private String startDate;
	private String endDate;
	private String priority;
	private long numbOfTasks;
	private String completed;
	
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public long getNumbOfTasks() {
		return numbOfTasks;
	}
	public void setNumbOfTasks(long numbOfTasks) {
		this.numbOfTasks = numbOfTasks;
	}
	
}
