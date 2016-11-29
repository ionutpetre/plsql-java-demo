package ro.ionutpetre.controller;

import ro.ionutpetre.database.DbCredentials;
import ro.ionutpetre.model.Instructor;

public class InstructorSQL implements DbCredentials, InstructorSQLMap {

	public static String CREATE_INSTRUCTOR(Instructor i) {
		return String.format(
				"INSERT INTO %s.%s (%s,%s,%s,%s) VALUES ('%s','%s','%s','%s')",
				DB_NAME, TABLE_NAME, TABLE_COLUMN_ID, TABLE_COLUMN_USERNAME,
				TABLE_COLUMN_PASSWORD, TABLE_COLUMN_TITLE, i.getId(),
				i.getUsername(), i.getPassword(), i.getTitle());
	}

	public static String UPDATE_INSTRUCTOR(Instructor i) {
		return String.format(
				"UPDATE %s.%s SET %s='%s',%s='%s',%s='%s' WHERE %s='%s'",
				DB_NAME, TABLE_NAME, TABLE_COLUMN_USERNAME, i.getUsername(),
				TABLE_COLUMN_PASSWORD, i.getPassword(), TABLE_COLUMN_TITLE,
				i.getTitle(), TABLE_COLUMN_ID, i.getId());
	}

	public static String DELETE_INSTRUCTOR(String instructorId) {
		return String.format("DELETE FROM %s.%s WHERE %s='%s'", DB_NAME,
				TABLE_NAME, TABLE_COLUMN_ID, instructorId);
	}

	public static String GET_ALL_INSTRUCTORS() {
		return String.format("SELECT * FROM %s.%s", DB_NAME, TABLE_NAME);
	}

}
