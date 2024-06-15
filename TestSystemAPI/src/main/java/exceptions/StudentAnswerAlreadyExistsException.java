package exceptions;

public class StudentAnswerAlreadyExistsException extends RuntimeException {
    public StudentAnswerAlreadyExistsException(String message) {
        super(message);
    }
}
