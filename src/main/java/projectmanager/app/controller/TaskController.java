package projectmanager.app.controller;



import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projectmanager.app.entity.Task;
import projectmanager.app.service.TaskServices;


@RestController
@RequestMapping("/TaskManager")

public class TaskController {

	@Autowired
	private TaskServices taskServices;
	
	  @PostMapping( path="/addTask", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Task> addTask( @RequestBody Task task) {
	        return ResponseEntity.ok(taskServices.taskSave(task));
	    }
	  @GetMapping(path ="/getAllTasks", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Task> getAllTasks() {
		  return taskServices.getAllTasks();
	  }
	  @GetMapping(path ="/findTasksByDate/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Task> findTasksByDate(@PathVariable("startDate")Date startDate,@PathVariable("endDate")Date endDate) {
		  return taskServices.findTasksByDate(startDate,endDate);
	  }
	  @GetMapping(path ="/findByTaskID/{taskID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Task> findByTaskID(@PathVariable("taskID")String taskID) {
		  return taskServices.findByTaskID(taskID);
	  }
	  @GetMapping(path ="/findTasksByPriority/{startRange}/{endRange}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Task> findTasksByPriority(@PathVariable("startRange")int startRange,@PathVariable("endRange") int endRange) {
		  return taskServices.findTasksByPriority(startRange, endRange);
	  }
	  @GetMapping(path ="/findTasksByParentID/{parentID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Task> findTasksByParentID(@PathVariable("parentID")int parentID) {
		  return taskServices.findTasksByParentID(parentID);
	  }
	  @GetMapping(path ="/getTask/{taskID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public Task getTask(@PathVariable String taskID) {
		  return taskServices.getTask(taskID);
	  }
	  @DeleteMapping(path ="/deleteTask/{taskID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public void deleteTask(@PathVariable String taskID) {
		  taskServices.deleteTask(taskID);
	  }
	  @PutMapping(path ="/updateTask/{taskID}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> updateTask( @RequestBody Task task, @PathVariable String taskID) {
		  return taskServices.updateTask(task, taskID);
	  }
	  @GetMapping
	  public String getData() {
		  return "Welcome to Task manager";
	  }
}
