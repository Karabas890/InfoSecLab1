package itmo.ru.infosec.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Password is incorrect.");
    }
}
