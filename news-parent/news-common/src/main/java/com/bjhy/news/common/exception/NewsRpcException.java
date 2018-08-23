package com.bjhy.news.common.exception;

/**
 * news-rpc异常类
 *
 * @author wubo
 */
public final class NewsRpcException extends RuntimeException {

    public static final int UNKNOWN_EXCEPTION = 0;//未知异常码
    public static final int NETWORK_EXCEPTION = 1;//网络异常码
    public static final int TIMEOUT_EXCEPTION = 2;//超时异常码
    public static final int BIZ_EXCEPTION = 3;//业务异常码
    public static final int FORBIDDEN_EXCEPTION = 4;//禁用异常码
    public static final int SERIALIZATION_EXCEPTION = 5;//序列化异常码
    private static final long serialVersionUID = 7815426752583648734L;
    private int code; // NewsRpcException不能有子类，异常类型用ErrorCode表示，以便保持兼容。

    public NewsRpcException() {
        super();
    }

    public NewsRpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsRpcException(String message) {
        super(message);
    }

    public NewsRpcException(Throwable cause) {
        super(cause);
    }

    public NewsRpcException(int code) {
        super();
        this.code = code;
    }

    public NewsRpcException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public NewsRpcException(int code, String message) {
        super(message);
        this.code = code;
    }

    public NewsRpcException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isBiz() {
        return code == BIZ_EXCEPTION;
    }

    public boolean isForbidded() {
        return code == FORBIDDEN_EXCEPTION;
    }

    public boolean isTimeout() {
        return code == TIMEOUT_EXCEPTION;
    }

    public boolean isNetwork() {
        return code == NETWORK_EXCEPTION;
    }

    public boolean isSerialization() {
        return code == SERIALIZATION_EXCEPTION;
    }
}