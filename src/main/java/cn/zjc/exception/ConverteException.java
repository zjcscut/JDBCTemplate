package cn.zjc.exception;

/**
 * @author zjc
 * @version 2016/8/31 0:03
 * @description 转换异常
 */
public class ConverteException extends RuntimeException {

	public ConverteException(String message) {
		super(message);
	}

	public ConverteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConverteException(String sfm, Throwable cause, Object... args) {
		super(String.format(sfm, args), cause);
	}

	public ConverteException(String sfm, Object... args) {
		super(String.format(sfm, args));
	}
}
