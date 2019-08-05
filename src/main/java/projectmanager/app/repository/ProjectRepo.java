package projectmanager.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projectmanager.app.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, String> {
	
	public Project findByProjectID(String projectID);

}
