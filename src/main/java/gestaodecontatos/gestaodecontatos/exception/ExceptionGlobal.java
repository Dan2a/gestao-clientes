package gestaodecontatos.gestaodecontatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionGlobal {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Obtém o primeiro erro de validação, já que estamos tratando um único erro, como o CPF
        FieldError error = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);

        // Se houver erro, retornamos a mensagem
        if (error != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getDefaultMessage());
        }

        // Se não houver erro, retornamos uma mensagem genérica
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação desconhecido");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}


