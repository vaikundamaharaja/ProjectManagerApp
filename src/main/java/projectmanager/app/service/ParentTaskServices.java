package projectmanager.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import projectmanager.app.entity.ParentTask;
import projectmanager.app.repository.ParentTaskRepo;
import projectmanager.app.util.IDGenerator;

@Service
public class ParentTaskServices {
	
	@Autowired
	private ParentTaskRepo parentTaskRepo;
	
	 public ParentTask parentTaskSave(ParentTask parentTask) {
		 String parentID=IDGenerator.gereteParentID(parentTaskRepo.findAll());
		 parentTask.setParentID(parentID);
	        return parentTaskRepo.save(parentTask);
	    }
	 
	 public List<ParentTask> getAllParentTasks() {
	        return parentTaskRepo.findAll();
	    }
	 
	 public ParentTask getParentTask(String parentID) {
	        return parentTaskRepo.findByParentID(parentID);
	    }
	 
	 public void deleteParentTask(String parentID) {
		 parentTaskRepo.deleteById(parentID);
	 }
	 
	 public ResponseEntity<Object> updateParentTask(ParentTask parentTask, String parentID) {		 
		 ParentTask parTask= parentTaskRepo.findByParentID(parentID);
		 if(parTask ==null) 
			 return ResponseEntity.notFound().build();
		 parTask.setParentID(parentTask.getParentID());
		 parTask.setParentTask(parentTask.getParentTask());
		 parentTaskRepo.save(parTask);
		 return ResponseEntity.noContent().build();
	 }

}
