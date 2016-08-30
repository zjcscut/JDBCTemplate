package cn.zjc.jdbc.pool.common;


import cn.zjc.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhangjinci
 * @version 2016/8/29 16:44
 * @function
 */
public class FastDaoConnectionFactory implements AutoCloseable {

    private static FastDaoConnectionFactory factory = new FastDaoConnectionFactory();

    private FastDaoConnectionFactory() {

    }

    public static FastDaoConnectionFactory getInstance() {
        return factory;
    }

    public FastDaoConnectionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final Logger log = LoggerFactory.getLogger(FastDaoConnectionFactory.class);

    public static ThreadLocal<Connection> container = new ThreadLocal<>();

    private DataSource dataSource;
    private Connection connection;

    public Connection getConnection() {
        if (container.get() == null) {
            try {
                if (dataSource == null) {
                    throw new DaoException("datasource could not be null");
                }
                connection = dataSource.getConnection();
                container.set(connection);
                return connection;
            } catch (SQLException e) {
                log.error("get connection failed:" + e.getMessage());
            }
        }
        return null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void close() throws Exception {
        if (container.get() != null) {
            connection = container.get();
            connection.close();
        }
    }
}
