package exceptions;

public class UserAlreadyExistsException extends RuntimeException{

	public UserAlreadyExistsException() {
		super("user already exists");
	}
}
