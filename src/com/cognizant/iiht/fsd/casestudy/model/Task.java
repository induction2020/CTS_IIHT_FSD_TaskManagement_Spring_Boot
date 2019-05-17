package com.cognizant.iiht.fsd.casestudy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="Task")
@Table(name="TASK_1")
public class Task implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TASK_ID")
	private long taskId;
	
	@Column(name="TASK")
	private String task;
	
	@Column(name="START_DATE")
	private String startDate;
	
	@Column(name="END_DATE")
	private String endDate;
	
	@Column(name="PRIORITY")
	private int priority;

	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="PARENT_ID")
	private ParentTaskDo parentTaskDo;
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY) // , fetch = FetchType.LAZY
	@JoinColumn(name="PROJECT_ID")
	private Project project;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ParentTaskDo getParentTaskDo() {
		return parentTaskDo;
	}

	public void setParentTaskDo(ParentTaskDo parentTaskDo) {
		this.parentTaskDo = parentTaskDo;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
	
	
	
	
	
}
