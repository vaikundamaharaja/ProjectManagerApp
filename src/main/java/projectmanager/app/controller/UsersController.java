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

import projectmanager.app.entity.Users;
import projectmanager.app.service.UsersServices;

@RestController
@RequestMapping("/Users")
public class UsersController {

	@Autowired
	private UsersServices usersServices;
	
	@PostMapping( path="/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> addParentTask( @RequestBody Users user) {
        return ResponseEntity.ok(usersServices.userSave(user));
    }
  @GetMapping(path ="/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Users> getAllUsers() {
	  return usersServices.getAllUsers();
  }
  @GetMapping(path ="/getUser/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Users getUser(@PathVariable String userID) {
	  return usersServices.getUser(userID);
  }
  @DeleteMapping(path ="/deleteUser/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void deleteUser(@PathVariable String userID) {
	  usersServices.deleteUser(userID);
  }
  @PutMapping(path ="/updateUser/{parentTaskID}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> updateUser( @RequestBody Users user, @PathVariable String userID) {
	  return usersServices.updateUser(user, userID);
  }
}
