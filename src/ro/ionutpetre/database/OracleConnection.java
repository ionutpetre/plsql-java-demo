package ro.ionutpetre.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleConnection implements DbConnection, DbCredentials {

	private static final String CONNECTION_STRING = "jdbc:oracle:thin:@"
			+ DB_HOSTNAME + ":" + DB_PORT + "/" + DB_SID;
	private static OracleConnection dbInstance = null;
	private Connection dbConnection = null;
	private Statement statement = null;

	private OracleConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DB_DRIVER_NAME);
		open();
	}

	public static OracleConnection getInstance() throws ClassNotFoundException,
			SQLException {
		if (dbInstance == null) {
			dbInstance = new OracleConnection();
		}
		return dbInstance;
	}

	public Connection getConnection() {
		return dbConnection;
	}

	public void open() throws SQLException {
		dbConnection = DriverManager.getConnection(CONNECTION_STRING, DB_USER,
				DB_PASSWORD);
		System.out.println("Connected to: " + CONNECTION_STRING);
	}

	public void close() throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
			System.out.println("Disconnected from: " + CONNECTION_STRING);
		}
	}

	public void executeSql(String sql) throws SQLException {
		statement = dbConnection.createStatement();
		statement.executeUpdate(sql);
	}

	public ResultSet querySql(String sql) throws SQLException {
		statement = dbConnection.createStatement();
		return statement.executeQuery(sql);
	}

	public void executePlSql(String plsql) throws SQLException {
		statement = dbConnection.createStatement();
		statement.execute(plsql);
	}

}
