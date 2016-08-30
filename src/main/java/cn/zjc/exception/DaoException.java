package cn.zjc.exception;

/**
 * @author zhangjinci
 * @version 2016/8/29 11:01
 * @function dao异常
 */
public class DaoException  extends RuntimeException{

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
