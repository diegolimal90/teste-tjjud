package exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class OutException extends RuntimeException {
    private List<Errors> errorList = new ArrayList<>();

    public OutException(String message) {
        super(message);
    }

    public OutException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutException(String message, Errors... errors) {
        super(message);
        this.errorList = Arrays.asList(errors);
    }

    public OutException(String message, Throwable cause, Errors... errors) {
        super(message, cause);
        this.errorList = Arrays.asList(errors);
    }
}
