package projectmanager.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="parent_task")
public class ParentTask {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "parent_id")
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(
        name = "book_seq", 
        strategy = "projectmanager.app.util.ParentIDGenerator")*/
	private String parentID; 
	@Column(name = "parent_task")
	private String parentTask;

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

}
