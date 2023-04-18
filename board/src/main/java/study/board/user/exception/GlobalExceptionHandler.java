package study.board.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeExceptionHandle(Exception e){
        log.error("RUNTIME_EXCEPTION: {}", e.getMessage());
        return e.getMessage();
    }
}
