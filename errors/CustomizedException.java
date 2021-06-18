package errors;

public class CustomizedException extends Exception{

    public CustomizedException(String message, int code) {
        super(message);
    }
}
