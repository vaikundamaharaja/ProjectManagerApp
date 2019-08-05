package projectmanager.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import projectmanager.app.entity.Project;
import projectmanager.app.repository.ProjectRepo;
import projectmanager.app.util.IDGenerator;

@Service
public class ProjectServices {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	public Project projectSave(Project project ) {
		String projectID=IDGenerator.gereteProjectID(projectRepo.findAll());
		project.setProjectID(projectID);
		return projectRepo.save(project);
	}
	
	 public List<Project> getAllProjects() {
	        return projectRepo.findAll();
	    }
	 
	 public Project getProject(String projectID) {
	        return projectRepo.findByProjectID(projectID);
	    }
	 
	 public void deleteProject(String parentID) {
		 projectRepo.deleteById(parentID);
	 }
	 
	 public ResponseEntity<Object> updateProject(Project project, String projectID) {		 
		 Project projectFound= projectRepo.findByProjectID(projectID);
		 if(projectFound ==null) 
			 return ResponseEntity.notFound().build();
		 projectFound.setProjectID(project.getProjectID());
		 projectFound.setProjectName(project.getProjectName());
		 projectFound.setStartDate(project.getStartDate());
		 projectFound.setEndDate(project.getEndDate());
		 projectFound.setPriority(project.getPriority());
		 projectRepo.save(projectFound);
		 return ResponseEntity.noContent().build();
	 }

}
