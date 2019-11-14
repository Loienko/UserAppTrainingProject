package net.ukr.dreamsicle.exception;

public class MyOwnException extends RuntimeException {
    public MyOwnException() {
        super();
    }

    public MyOwnException(String message) {
        super(message);
    }

    public MyOwnException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyOwnException(Throwable cause) {
        super(cause);
    }

    protected MyOwnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
