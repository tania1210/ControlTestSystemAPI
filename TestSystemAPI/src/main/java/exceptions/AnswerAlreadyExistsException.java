package exceptions;

public class AnswerAlreadyExistsException extends RuntimeException {
    public AnswerAlreadyExistsException(String message) {
        super(message);
    }
}
