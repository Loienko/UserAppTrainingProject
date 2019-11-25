package net.ukr.dreamsicle.exception;

public class DreamsicleException extends RuntimeException {

    private Integer errorCode;

    public DreamsicleException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DreamsicleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DreamsicleException(String message, Throwable cause, Integer errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DreamsicleException(String message) {
        super(message);
    }

    public DreamsicleException(Throwable cause) {
        super(cause);
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
