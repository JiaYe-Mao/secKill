package exception;

public class RepeatKilledException extends SecKillException {

    public RepeatKilledException(String message) {
        super(message);
    }

    public RepeatKilledException(String message, Throwable cause) {
        super(message, cause);
    }
}
