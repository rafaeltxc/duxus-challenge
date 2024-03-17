package br.com.duxusdesafio.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe para configuracao de erros personalizados
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Configuracao para excessoes gerais da aplicacao
     *
     * @param e Excessao lancada
     * @return Reposta com o status code e string com descricao do erro
     */
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<String> generalError(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal error: " + e.getMessage());
    }

    /**
     * Configuracao para excessao de entidade nao encontrada
     *
     * @param e Excessao lancada
     * @return Reposta com o status code e string com descricao do erro
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<String> objNotFound(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Object not found: " + e.getMessage());
    }

    /**
     * Configuracao para excessao de propriedade violada
     *
     * @param e Excessao lancada
     * @return Reposta com o status code e string com descricao do erro
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<String> constraintViolation(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad request: " + e.getMessage());
    }
}
