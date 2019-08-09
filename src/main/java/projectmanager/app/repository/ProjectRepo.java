package projectmanager.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import projectmanager.app.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, String>,JpaSpecificationExecutor<Project> {
	
	public Project findByProjectID(String projectID);

}
