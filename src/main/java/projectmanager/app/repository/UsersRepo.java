package projectmanager.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projectmanager.app.entity.Users;

public interface UsersRepo extends JpaRepository<Users, String> {

	public Users findByUserID(String userID);
}
