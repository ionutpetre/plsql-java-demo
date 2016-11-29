package ro.ionutpetre.model;

public class Learner extends User {

	private Integer year;
	private String group;

	public Learner(String id, String username, String password) {
		super(id, username, password);
	}

	public Learner(String id, String username, String password, Integer year,
			String group) {
		super(id, username, password);
		this.year = year;
		this.group = group;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String toString() {
		return "Learner [id=" + id + ", username=" + username + ", password="
				+ password + ", year=" + year + ", group=" + group + "]";
	}

}
