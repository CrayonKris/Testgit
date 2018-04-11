package cn.tedu.exception;

public class MsgException extends Exception {
	/**
	 * 自定义异常
	 * */
	public MsgException() {
		super();
	}
	public MsgException(String msg) {
		super(msg);
	}

}
