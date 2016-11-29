package ro.ionutpetre.model;

public class Instructor extends User {

	private String title;

	public Instructor() {
	}

	public Instructor(String id, String username, String password) {
		super(id, username, password);
	}

	public Instructor(String id, String username, String password, String title) {
		super(id, username, password);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return "Instructor [id=" + id + ", username=" + username
				+ ", password=" + password + ", title=" + title + "]";
	}

}
