package exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private List<Errors> errorList;

    public BusinessException(String message, Errors... errors) {
        super(message);
        this.errorList = Arrays.asList(errors);
    }

    public BusinessException(String message, List<Errors> errorList, Throwable cause) {
        super(message, cause);
        this.errorList = errorList;
    }
}
