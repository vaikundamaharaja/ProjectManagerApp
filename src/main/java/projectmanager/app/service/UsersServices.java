package projectmanager.app.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import projectmanager.app.entity.Users;
import projectmanager.app.repository.UsersRepo;
import projectmanager.app.util.IDGenerator;

@Service
public class UsersServices {

	@Autowired
	private UsersRepo usersRepo;
	
	public Users userSave(Users user) {
		String userID=IDGenerator.gereteUserID(usersRepo.findAll());
		user.setUserID(userID);
        return usersRepo.save(user);
    }
 
 public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }
 
 public Users getUser(String userID) {
        return usersRepo.findByUserID(userID);
    }
 
 public void deleteUser(String userID) {
	 usersRepo.deleteById(userID);
 }
 
 public ResponseEntity<Object> updateUser(Users user, String userID) {		 
	 Users userFound= usersRepo.findByUserID(userID);
	 if(userFound ==null) 
		 return ResponseEntity.notFound().build();
	 userFound.setUserID(user.getUserID());
	 userFound.setFirstName(user.getFirstName());
	 userFound.setLastName(user.getLastName());
	 userFound.setEmployeeID(user.getEmployeeID());
	 usersRepo.save(userFound);
	 return ResponseEntity.noContent().build();
 }
 public List<Users> getUsersSortByFirstName(){
		List<Users> users = usersRepo.findAll();
		users.sort(Comparator.comparing(Users::getFirstName));
		return users;
	}
	public List<Users> getTasksSortByLastName(){
		List<Users> users = usersRepo.findAll();
		users.sort(Comparator.comparing(Users::getLastName));
		return users;
	}
	public List<Users> getTasksSortByEmployeeID(){
		List<Users> users = usersRepo.findAll();
		users.sort(Comparator.comparing(Users::getEmployeeID));
		return users;
	}

}
