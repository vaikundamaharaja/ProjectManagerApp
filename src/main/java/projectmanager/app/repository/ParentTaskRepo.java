package projectmanager.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import projectmanager.app.entity.ParentTask;


public interface ParentTaskRepo extends JpaRepository<ParentTask, String> {

	public ParentTask findByParentID(String parentID);
	}
