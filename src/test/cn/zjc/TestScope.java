package cn.zjc;

import cn.zjc.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author zjc
 * @version 2016/8/27 21:59
 * @description
 */
public class TestScope {


	@Test
	public void testDriver() throws SQLException {
		Driver driver = new com.mysql.jdbc.Driver();

		String url = "jdbc:mysql://localhost:3306/jdbc?characterEncoding=utf8&useSSL=true";

		Properties properties = new Properties();
		properties.put("user", "root");
		properties.put("password", "root");

		Connection con = driver.connect(url, properties);

		System.out.println(con);
	}

	public Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException, IOException {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		try (InputStream in = getClass().getClassLoader().
				getResourceAsStream("jdbc.properties")) {

			Properties info = new Properties();
			info.load(in);

			driverClass = info.getProperty("driver");
			jdbcUrl = info.getProperty("jdbcUrl");
			user = info.getProperty("user");
			password = info.getProperty("password");

			Properties properties = new Properties();
			properties.put("user", user);
			properties.put("password", password);


			Driver driver = (Driver) Class.forName(driverClass).newInstance();

			Connection connection = driver.connect(jdbcUrl, properties);

			return connection;
		}
	}

	@Test
	public void testCommon() throws ClassNotFoundException, SQLException, InstantiationException, IOException, IllegalAccessException {
		System.out.println(getConnection());
	}


	@Test
	public void testDriverManager() throws IOException, SQLException, ClassNotFoundException {
		System.out.println(getConnectionByDriverManager());
	}

	public Connection getConnectionByDriverManager() throws IOException, ClassNotFoundException, SQLException {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		try (InputStream in = this.getClass().getClassLoader().
				getResourceAsStream("jdbc.properties")) {

			Properties info = new Properties();
			info.load(in);

			driverClass = info.getProperty("driver");
			jdbcUrl = info.getProperty("jdbcUrl");
			user = info.getProperty("user");
			password = info.getProperty("password");

			//加载数据库驱动
			Class.forName(driverClass);

			Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

//			System.out.println(connection);
			return connection;
		}
	}

	@Test
	public void testStastment() throws Exception {
		//获取数据库连接
		Connection connection = null;
		Statement statement = null;


		try {
			connection = getConnectionByDriverManager();

			//准备插入的sql
//			String sql = "INSERT INTO USER (NAME,EMAIL,BIRTH) VALUES " +
//					"('zjc','zjcscut@163.com','1993-3-10')";
			String sql = "UPDATE USER SET NAME ='zjcscut' WHERE ID = 1";

			//创建Statement对象
			statement = connection.createStatement();

			//执行sql
			statement.executeUpdate(sql);
		} catch (Exception e) {

		} finally {
			try {

				//关闭statement
				if (null != statement) {
					statement.close();
				}
			} catch (Exception e) {

			} finally {
				//关闭连接
				if (null != connection) {
					connection.close();
				}
			}
		}
	}

	public void update(String sql) {
		//获取数据库连接
		Connection connection = null;
		Statement statement = null;


		try {
			connection = getConnectionByDriverManager();

			//创建Statement对象
			statement = connection.createStatement();

			//执行sql
			statement.executeUpdate(sql);
		} catch (Exception e) {

		} finally {
			try {

				//关闭statement
				if (null != statement) {
					statement.close();
				}
			} catch (Exception e) {

			} finally {
				//关闭连接
				if (null != connection) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}


	@Test
	public void TestTools() {
		JDBCTools.update("UPDATE USER SET NAME ='zjc' WHERE ID = 1");
	}

	@Test
	public void testResultSet() {
		String sql = "select id,name AS USERNAME,email,birth from USER where id = 1";
		try (Connection coon = JDBCTools.getConnectionByDriverManager();

			 Statement statement = coon.createStatement();
			 ResultSet re = statement.executeQuery(sql)) {

			//获取列详细信息
			ResultSetMetaData metaData = re.getMetaData();  //获取ResultSet元数据
			int count = metaData.getColumnCount();
			for (int i = 1; i < count; i++) {  //i从1开始
				System.out.println("列名:" + metaData.getColumnName(i));  //列名
				System.out.println("列类型名:" + metaData.getColumnTypeName(i)); //数据库类类型
				System.out.println("Catalog名:" + metaData.getCatalogName(i));
				System.out.println("ColumnClass名:" + metaData.getColumnClassName(i)); //java类型
				System.out.println("ColumnLabel名:" + metaData.getColumnLabel(i)); //列label名称,一般是自定义的别名
				System.out.println("表名:" + metaData.getTableName(i));
				System.out.println("Scala名:" + metaData.getScale(i));
				System.out.println("Schema名:" + metaData.getSchemaName(i));
			}

			while (re.next()) {
				int id = re.getInt("ID");
				String name = re.getString("USERNAME");
				String email = re.getString("EMAIL");
				java.util.Date birth = re.getTimestamp("BIRTH");

				System.out.println(id);
				System.out.println(name);
				System.out.println(email);
				System.out.println(birth);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPrepareStatement() {
		String sql = "INSERT INTO `USER` (`NAME`,`EMAIL`,`BIRTH`) VALUES(?,?,?)";
		try (Connection connection = getConnectionByDriverManager();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			//设置参数
			preparedStatement.setString(1, "evan");
			preparedStatement.setString(2, "evan@163.com");
			preparedStatement.setObject(3, new Date());

			preparedStatement.executeUpdate(); //这里不要再提交一次sql

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestSQLInject() {

		String NAME = "'' OR 1=1";
		String sql = "Select * from user where NAME = " + NAME + "";
		try (Statement statement = getConnectionByDriverManager().createStatement();
			 ResultSet re = statement.executeQuery(sql)) {

			while (re.next()) {
				System.out.println(re.getInt("ID"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestResultSetA() {
		String sql = "SELECT ID AS id,NAME AS name,EMAIL AS email , BIRTH AS birth FROM USER WHERE ID = ?";
		ResultSet resultSet = null;
		try (Connection connection = getConnectionByDriverManager();
			 PreparedStatement statement = connection.prepareStatement(sql);
		) {

			statement.setInt(1, 1);

			resultSet = statement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();


			Map<String, Object> values = new HashMap<>();

			while (resultSet.next()) {
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					values.put(metaData.getColumnLabel(i + 1), resultSet.getObject(metaData.getColumnLabel(i + 1)));
				}
			}

			Class clazz = User.class;

			Object o = clazz.newInstance();
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				ReflectionUtils.setFieldValue(o, entry.getKey(), entry.getValue());
			}


			System.out.println(o);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Test
	public void testGet() {
		String sql = "SELECT ID AS id,NAME AS name,EMAIL AS email , BIRTH AS birth FROM USER WHERE ID = ?";
		User u = get(User.class, sql, 1);
		System.out.println(u);
	}

	public <T> T get(Class<T> T, String sql, Object... args) {
		T entity = null;

		ResultSet resultSet = null;
		try (Connection connection = getConnectionByDriverManager();
			 PreparedStatement statement = connection.prepareStatement(sql);
		) {


			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			resultSet = statement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();

			Map<String, Object> values = new HashMap<>();

			while (resultSet.next()) {
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					values.put(metaData.getColumnLabel(i + 1), resultSet.getObject(metaData.getColumnLabel(i + 1)));
				}
			}


			entity = T.newInstance();
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				ReflectionUtils.setFieldValue(entity, entry.getKey(), entry.getValue());
			}


			return entity;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Test
	public void TestGetList(){
		String sql = "SELECT ID AS id,NAME AS name,EMAIL AS email , BIRTH AS birth FROM USER WHERE ID > 0";
		List<User> list = getList(User.class,sql);
		System.out.println(list);
	}

	public <T> List<T> getList(Class<T> t, String sql, Object... args) {


		ResultSet resultSet = null;
		try (Connection connection = getConnectionByDriverManager();
			 PreparedStatement statement = connection.prepareStatement(sql);
		) {

			List<T> list = new ArrayList<>();

			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			resultSet = statement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();


			while (resultSet.next()) {
				T entity = t.newInstance();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					ReflectionUtils.setFieldValue(entity, metaData.getColumnLabel(i + 1), resultSet.getObject(i + 1));
				}
				list.add(entity);
			}


			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
