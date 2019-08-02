package projectmanager.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import projectmanager.app.entity.Task;



public interface TaskRepo extends JpaRepository<Task, String> ,JpaSpecificationExecutor<Task> {
	
	public Task findByTaskID(String taskID);
	public List<Task> findTasksByParentID(String parentID);

}
