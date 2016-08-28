package cn.zjc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zjc
 * @version 2016/8/28 12:13
 * @description
 */
public class JDBCTools {

	public static Connection getConnectionByDriverManager() throws IOException, ClassNotFoundException, SQLException {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		try (InputStream in = JDBCTools.class.getClassLoader().
				getResourceAsStream("jdbc.properties")) {

			Properties info = new Properties();
			info.load(in);

			driverClass = info.getProperty("driver");
			jdbcUrl = info.getProperty("jdbcUrl");
			user = info.getProperty("user");
			password = info.getProperty("password");

			//加载数据库驱动
			Class.forName(driverClass);

			return DriverManager.getConnection(jdbcUrl, user, password);
		}
	}


	public static void update(String sql) {
		try (Connection connection = getConnectionByDriverManager();
			 Statement statement = connection.createStatement()
		) {
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
