package ro.ionutpetre.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbConnection {

	void open() throws SQLException;

	Connection getConnection();
	
	void close() throws SQLException;

	void executeSql(String sql) throws SQLException;
	
	ResultSet querySql(String sql) throws SQLException;
	
	void executePlSql(String plsql) throws SQLException;
}
