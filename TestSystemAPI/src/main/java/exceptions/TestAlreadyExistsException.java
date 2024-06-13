package exceptions;

public class TestAlreadyExistsException extends RuntimeException {

    public TestAlreadyExistsException(String message) {
        super(message);
    }
}
