package ph.proj.aep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erros de validação Bean Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<Map<String, String>> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> {
                    Map<String, String> erro = new HashMap<>();
                    erro.put("campo", fe.getField());
                    erro.put("mensagem", fe.getDefaultMessage());
                    return erro;
                })
                .toList();

        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("erros", erros);

        return ResponseEntity.badRequest().body(body);
    }

    // Recurso não encontrado
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "Recurso não encontrado";

        boolean isNotFound = msg.toLowerCase().contains("não encontrado")
                || msg.toLowerCase().contains("nao encontrado");

        int httpStatus = isNotFound ? 404 : 400;

        Map<String, Object> body = new HashMap<>();
        body.put("status", httpStatus);
        body.put("erro", msg);

        return ResponseEntity.status(httpStatus).body(body);
    }

    // Fallback genérico 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 500);
        body.put("erro", "Erro interno do servidor");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
