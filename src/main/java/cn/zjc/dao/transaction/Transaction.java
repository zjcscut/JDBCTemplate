package cn.zjc.dao.transaction;

/**
 * @author zhangjinci
 * @version 2016/8/29 19:51
 * @function
 */
public interface Transaction {

    void start();

    void commit();

    void rollback();
}
