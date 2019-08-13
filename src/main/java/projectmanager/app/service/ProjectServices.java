package projectmanager.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;


import projectmanager.app.entity.Project;
import projectmanager.app.entity.Task;
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
		 projectFound.setEmployeeID(project.getEmployeeID());
		 projectRepo.save(projectFound);
		 return ResponseEntity.noContent().build();
	 }
	 
	 public List<Project> findProjectByName(String projectName) {
	        //return taskRepo.findTasksByPriority(startRange, endRange);
		 return projectRepo.findAll( new Specification<Project>(){
				
				@Override
				public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
		              
		               if(projectName!=null){
		                   predicates.add(criteriaBuilder.like(root.get("projectName"),"%"+projectName+"%"));
		               }
		               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			});
	    }
	 
	 public List<Project> getProjectsSortByStartDate(){
			List<Project> projects = projectRepo.findAll();
			projects.sort(Comparator.comparing(Project::getStartDate));
			return projects;
		}
		public List<Project> getProjectsSortByEndDate(){
			List<Project> projects = projectRepo.findAll();
			projects.sort(Comparator.comparing(Project::getEndDate));
			return projects;
		}
		public List<Project> getProjectsSortByPriority(){
			List<Project> projects = projectRepo.findAll();
			projects.sort(Comparator.comparing(Project::getPriority));
			return projects;
		}


}
