package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/omikuji", "m_ochi",
					"mizusugatr09");
			return connection;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
