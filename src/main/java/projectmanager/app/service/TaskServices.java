package projectmanager.app.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import projectmanager.app.entity.Task;
import projectmanager.app.repository.TaskRepo;
import projectmanager.app.util.IDGenerator;
@Service
public class TaskServices {
	
	@Autowired
	private TaskRepo taskRepo;
	
	 public Task taskSave(Task task) {
		 String taskID= IDGenerator.gereteTaskID(taskRepo.findAll());
		 task.setTaskID(taskID);
		 return taskRepo.save(task);
	    }
	 
	 public List<Task> findTasksByDate(Date startDate,Date endDate) {
	       // return taskRepo.findTasksByDate(startDate,endDate);
		// return null;
		 return taskRepo.findAll( new Specification<Task>() {
				
				@Override
				public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
		              
		               if(startDate!=null ){
		            	   predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("startDate"), startDate)));
		                   //predicates.add(criteriaBuilder.between(root.get("priority"),startRange,endRange));
		               }
		               if(endDate !=null ){
		            	   predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("endDate"), endDate)));
		                   //predicates.add(criteriaBuilder.between(root.get("priority"),startRange,endRange));
		               }
		               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			});
	    
	    }
	 public List<Task> findTasksByParentID(int parentID) {
	       // return taskRepo.findTasksByParentID(parentID);
		 return taskRepo.findAll( new Specification<Task>() {
				
				@Override
				public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
		              
		              // if(parentID !=null ){
		            	 //  predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("parentID"), parentID)));
		                   //predicates.add(criteriaBuilder.between(root.get("priority"),startRange,endRange));
		             //  }
		               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			});
	    
	    }
	 public List<Task> findTasksByPriority(int startRange, int endRange) {
	        //return taskRepo.findTasksByPriority(startRange, endRange);
		 return taskRepo.findAll( new Specification<Task>() {
				
				@Override
				public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
		              
		               if(startRange>=0 && endRange >=0 ){
		                   predicates.add(criteriaBuilder.between(root.get("priority"),startRange,endRange));
		               }
		               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			});
	    }
	 public List<Task> findByTaskID(String taskID) {
		 	Task t = taskRepo.findByTaskID(taskID);
		 	List<Task> task =new ArrayList<Task>();
		 	task.add(t);
	        return task;
	    }
	 
	 public List<Task> getAllTasks() {
		return taskRepo.findAll();
	    }
	 
	 public Task getTask(String taskID) {
	        return taskRepo.findByTaskID(taskID);
	    }
	 
	 public void deleteTask(String taskID) {
		 taskRepo.deleteById(taskID);
	 }
	 
	 public ResponseEntity<Object> updateTask(Task task, String taskID) {		 
		 Task findTask= taskRepo.findByTaskID(taskID);
		 if(findTask ==null) 
			 return ResponseEntity.notFound().build();
		 //Have to work
		 findTask.setTaskID(task.getTaskID());
		 findTask.setParentID(task.getParentID());
		 findTask.setProjectID(task.getProjectID());
		 findTask.setTask(task.getTask());
		 findTask.setEndDate(task.getEndDate());
		 findTask.setStartDate(task.getStartDate());
		 findTask.setPriority(task.getPriority());
		 findTask.setStatus(task.getStatus());
		 findTask.setUserID(task.getUserID());
		 taskRepo.save(findTask);
		 return ResponseEntity.noContent().build();
	 }

}
