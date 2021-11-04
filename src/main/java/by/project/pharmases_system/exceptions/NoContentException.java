package by.project.pharmases_system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends Exception{
    public NoContentException(String message) {
        super(message);
    }
}
