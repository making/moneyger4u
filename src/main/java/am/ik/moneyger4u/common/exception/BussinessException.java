package am.ik.moneyger4u.common.exception;

public class BussinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BussinessException() {
        super();
    }

    public BussinessException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(Throwable cause) {
        super(cause);
    }

}
