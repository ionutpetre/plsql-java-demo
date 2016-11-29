package ro.ionutpetre.controller;

import java.sql.SQLException;
import java.util.List;

import ro.ionutpetre.model.Instructor;

public interface InstructorService {

	void createInstructor(Instructor i) throws SQLException;

	void updateInstructor(Instructor i) throws SQLException;

	void deleteInstructor(String instructorId) throws SQLException;
	
	List<Instructor> getAllInstructors() throws SQLException;
	
	int getNoOfInstructors() throws SQLException;
	
	void close() throws SQLException;

}
