package cn.zjc.jdbc.exception;

/**
 * @author zhangjinci
 * @version 2016/8/29 11:01
 * @function
 */
public class DaoException  extends RuntimeException{

    private String msg;
    private String code;
    private transient Throwable e;

    public DaoException() {
        super();
    }

    public DaoException(String msg, String code) {
        super();
        this.msg = msg;
        this.code = code;
    }

    public DaoException(String msg, String code, Throwable e) {
        super();
        this.msg = msg;
        this.code = code;
        this.e = e;
    }

    public DaoException(String msg, Throwable e) {
        super();
        this.msg = msg;
        this.e = e;
    }

    public DaoException(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
