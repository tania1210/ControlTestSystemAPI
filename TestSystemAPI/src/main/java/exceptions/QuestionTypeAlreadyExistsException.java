package exceptions;

public class QuestionTypeAlreadyExistsException extends RuntimeException {
    public QuestionTypeAlreadyExistsException(String message) {
        super(message);
    }
}
