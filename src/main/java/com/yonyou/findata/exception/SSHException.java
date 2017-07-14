package com.yonyou.findata.exception;

/**
 * @Desc  ssh异常类
 * @Author pizhihui
 * @Date 2017/4/19
 */
public class SSHException extends Exception {
    public SSHException() {
        super();
    }

    public SSHException(String message) {
        super(message);
    }

    public SSHException(String message, Throwable cause) {
        super(message, cause);
    }

    public SSHException(Throwable cause) {
        super(cause);
    }

}
