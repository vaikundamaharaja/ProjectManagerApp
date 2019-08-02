package projectmanager.app.controller;

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

import projectmanager.app.entity.Project;
import projectmanager.app.service.ProjectServices;

@RestController
@RequestMapping("/Project")
public class ProjectController {
	
	@Autowired
	private ProjectServices projectServices;
	
	  @PostMapping( path="/addProject", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Project> addProject( @RequestBody Project project) {
	        return ResponseEntity.ok(projectServices.projectSave(project));
	    }
	  @GetMapping(path ="/getAllProjects", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Project> getAllProjects() {
		  return projectServices.getAllProjects();
	  }
	  @GetMapping(path ="/getProject/{projectID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public Project getProject(@PathVariable String projectID) {
		  return projectServices.getProject(projectID);
	  }
	  @DeleteMapping(path ="/deleteProject/{projectID}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public void deleteProject(@PathVariable String projectID) {
		  projectServices.deleteProject(projectID);
	  }
	  @PutMapping(path ="/updateProject/{projectID}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> updateProject( @RequestBody Project project, @PathVariable String projectID) {
		  return projectServices.updateProject(project, projectID);
	  }

}
