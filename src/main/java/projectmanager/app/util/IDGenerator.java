package projectmanager.app.util;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import projectmanager.app.entity.ParentTask;
import projectmanager.app.entity.Project;
import projectmanager.app.entity.Task;
import projectmanager.app.entity.Users;
import projectmanager.app.service.ParentTaskServices;

public class IDGenerator {
	
	/*
	 * Generating parent id sequence
	 */
	public static String gereteParentID(List<ParentTask> tasks) {
		String parentID="";
		if(tasks.isEmpty()) {
			parentID="P001";
		}else {
		List<Integer> ids = tasks.stream().map(t-> 
		Integer.parseInt(t.getParentID().substring(1))).collect(Collectors.toList());
		int max = getMaxValue(ids);
		if(max<10) {
			parentID= "P00"+max;
		}else if(max<100) {
			parentID= "P0"+max;
		}
		else {
			parentID= "P"+max;
		}
		}
		return parentID;
	}
	
	public static String gereteUserID(List<Users> tasks) {
		String parentID="";
		if(tasks.isEmpty()) {
			parentID="U001";
		}else {
		List<Integer> ids = tasks.stream().map(t-> 
		Integer.parseInt(t.getUserID().substring(1))).collect(Collectors.toList());
		int max = getMaxValue(ids);
		if(max<10) {
			parentID= "U00"+max;
		}else if(max<100) {
			parentID= "U0"+max;
		}
		else {
			parentID= "U"+max;
		}
		}
		return parentID;
	}
	
	public static String gereteTaskID(List<Task> tasks) {
		String parentID="";
		if(tasks.isEmpty()) {
			parentID="T001";
		}else {
		List<Integer> ids = tasks.stream().map(t-> 
		Integer.parseInt(t.getTaskID().substring(1))).collect(Collectors.toList());
		int max = getMaxValue(ids);
		if(max<10) {
			parentID= "T00"+max;
		}else if(max<100) {
			parentID= "T0"+max;
		}
		else {
			parentID= "T"+max;
		}
		}
		return parentID;
	}
	
	public static String gereteProjectID(List<Project> tasks) {
		String parentID="";
		if(tasks.isEmpty()) {
			parentID="PR001";
		}else {
		List<Integer> ids = tasks.stream().map(t-> 
		Integer.parseInt(t.getProjectID().substring(2))).collect(Collectors.toList());
		int max = getMaxValue(ids);
		if(max<10) {
			parentID= "PR00"+max;
		}else if(max<100) {
			parentID= "PR0"+max;
		}
		else {
			parentID= "PR"+max;
		}
		}
		return parentID;
	}
	public static int getMaxValue(List<Integer> ids) {
		return ids.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax()+1;
	}
	
}
