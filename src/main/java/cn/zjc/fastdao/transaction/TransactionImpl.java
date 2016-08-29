package cn.zjc.fastdao.transaction;


import cn.zjc.jdbc.exception.DaoException;
import cn.zjc.jdbc.pool.common.FastDaoConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhangjinci
 * @version 2016/8/29 19:52
 * @function
 */
public class TransactionImpl implements Transaction {

    @Override
    public void start() {
        System.out.println("开始事务");
        try (Connection c = FastDaoConnectionFactory.getInstance().getConnection()){
            if (c == null){
                throw new DaoException("start Transaction failed");
            }
            c.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    @Override
    public void commit() {
        System.out.println("提交事务"
        );
        try(Connection c = FastDaoConnectionFactory.container.get()) {
            if (c == null){
                throw new DaoException("commit Transaction failed");
            }
            c.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    @Override
    public void rollback() {
        try (Connection c = FastDaoConnectionFactory.container.get()){
            if (c == null){
                throw new DaoException("");
            }
            c.rollback();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            FastDaoConnectionFactory.container.remove();
        }
    }
}
