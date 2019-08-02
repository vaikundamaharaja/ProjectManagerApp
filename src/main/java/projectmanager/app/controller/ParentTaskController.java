package projectmanager.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import projectmanager.app.entity.ParentTask;
import projectmanager.app.service.ParentTaskServices;

@RestController
@RequestMapping("/ParentTaskManager")
public class ParentTaskController {
	
	@Autowired
	private ParentTaskServices parentTaskServices;
	
	  @PostMapping( path="/addParentTask", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<ParentTask> addParentTask( @RequestBody ParentTask parentTask) {
	        return ResponseEntity.ok(parentTaskServices.parentTaskSave(parentTask));
	    }
	  @GetMapping(path ="/getAllParentTasks", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<ParentTask> getAllParentTasks() {
		  return parentTaskServices.getAllParentTasks();
	  }
	  @GetMapping(path ="/getParentTask/{parentTaskID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ParentTask getParentTask(@PathVariable String parentTaskID) {
		  return parentTaskServices.getParentTask(parentTaskID);
	  }
	  @DeleteMapping(path ="/deleteParentTask/{parentTaskID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public void deleteParentTask(@PathVariable String parentTaskID) {
		  parentTaskServices.deleteParentTask(parentTaskID);
	  }
	  @PutMapping(path ="/updateParentTask/{parentTaskID}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> updateParentTask( @RequestBody ParentTask parentTask, @PathVariable String parentTaskID) {
		  return parentTaskServices.updateParentTask(parentTask, parentTaskID);
	  }
	  @GetMapping
	  public String getData() {
		//  System.out.println(ParentIDGenerator.gereteParentID(parentTaskServices.getAllParentTasks()));
		  return "Welcome to Parent Task manager";
	  }

}
