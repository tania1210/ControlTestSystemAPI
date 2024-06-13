package exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("password is too short");
    }
}
