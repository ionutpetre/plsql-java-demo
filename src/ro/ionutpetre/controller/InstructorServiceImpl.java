package ro.ionutpetre.controller;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ro.ionutpetre.database.DbConnection;
import ro.ionutpetre.database.OracleConnection;
import ro.ionutpetre.model.Instructor;

public class InstructorServiceImpl implements InstructorService,
		InstructorSQLMap {

	private DbConnection db = null;

	public InstructorServiceImpl() throws ClassNotFoundException, SQLException {
		db = OracleConnection.getInstance();
	}

	public void createInstructor(Instructor i) throws SQLException {
		db.executeSql(InstructorSQL.CREATE_INSTRUCTOR(i));
		System.out.println(String.format("Created %s", i.toString()));
	}

	public void updateInstructor(Instructor i) throws SQLException {
		db.executeSql(InstructorSQL.UPDATE_INSTRUCTOR(i));
		System.out.println(String.format("Updated %s", i.toString()));
	}

	public void deleteInstructor(String instructorId) throws SQLException {
		db.executeSql(InstructorSQL.DELETE_INSTRUCTOR(instructorId));
		System.out.println(String.format("Deleted Instructor with id = %s",
				instructorId));
	}

	public List<Instructor> getAllInstructors() throws SQLException {
		ResultSet resultSet = db.querySql(InstructorSQL.GET_ALL_INSTRUCTORS());
		List<Instructor> instructors = new ArrayList<>();
		while (resultSet.next()) {
			Instructor instructor = new Instructor();
			instructor.setId(resultSet.getString(TABLE_COLUMN_ID));
			instructor.setUsername(resultSet.getString(TABLE_COLUMN_USERNAME));
			instructor.setPassword(resultSet.getString(TABLE_COLUMN_PASSWORD));
			instructor.setTitle(resultSet.getString(TABLE_COLUMN_TITLE));
			instructors.add(instructor);
		}
		resultSet.close();
		System.out.println(String.format("Get all Instructors %s",
				instructors.toString()));
		return instructors;
	}

	public int getNoOfInstructors() throws SQLException {
		int noOfInstructors = 0;
		CallableStatement callableStatement = null;
		callableStatement = db.getConnection().prepareCall(
				"{? = call nr_utilizatori()}");
		callableStatement.registerOutParameter(1, Types.INTEGER);
		callableStatement.execute();
		noOfInstructors = callableStatement.getInt(1);
		callableStatement.close();
		return noOfInstructors;
	}

	public void close() throws SQLException {
		db.close();
	}

}
