package exceptions;

public class GroupAlreadyExistsException extends RuntimeException {
    public GroupAlreadyExistsException(String message) {
        super(message);
    }
}
