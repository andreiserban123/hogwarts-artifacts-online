package ro.ase.ro.hogwartsartifactsonline.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.ase.ro.hogwartsartifactsonline.exceptions.ArtifactNotFoundException;
import ro.ase.ro.hogwartsartifactsonline.system.Result;
import ro.ase.ro.hogwartsartifactsonline.system.StatusCode;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArtifactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleArtifactNotFoundException(ArtifactNotFoundException e) {
        return new Result(
                false,
                StatusCode.NOT_FOUND,
                e.getMessage()
        );
    }
}
