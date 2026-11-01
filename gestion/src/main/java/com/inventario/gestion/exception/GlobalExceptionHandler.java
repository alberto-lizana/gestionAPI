package com.inventario.gestion.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.inventario.gestion.dto.ErrorExceptionResponseDto;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorExceptionResponseDto> handleIllegalState(HttpServletRequest request, IllegalStateException ex) {

        ErrorExceptionResponseDto errResponse = ErrorExceptionResponseDto
            .builder()
                .mensaje("Operación no permitida")
                .errores(List.of(ex.getMessage()))
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorExceptionResponseDto> handleValidation(HttpServletRequest request, MethodArgumentNotValidException ex) {

        List<String> err = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();

        ErrorExceptionResponseDto errResponse = ErrorExceptionResponseDto
            .builder()
                .mensaje("Error de validación")
                .errores(err)
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResponse);
    }

    @ExceptionHandler (RolNoEncontradoException.class)
    public ResponseEntity<ErrorExceptionResponseDto> handleRolNoEncontrado(HttpServletRequest request, RolNoEncontradoException ex){
        ErrorExceptionResponseDto err = ErrorExceptionResponseDto
            .builder()
                .mensaje("Rol no encontrado")
                .errores(List.of(ex.getMessage()))
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorExceptionResponseDto> handleNotFound(HttpServletRequest request, NoHandlerFoundException ex) {

        ErrorExceptionResponseDto errResponse = ErrorExceptionResponseDto
                                        .builder()
                                            .mensaje("Recurso no encontrado")
                                            .errores(List.of(ex.getMessage()))
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .timestamp(LocalDateTime.now())
                                            .path(request.getRequestURI())
                                        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorExceptionResponseDto> handleGeneralError(HttpServletRequest request, Exception ex) {

        ErrorExceptionResponseDto errResponse = ErrorExceptionResponseDto
                                        .builder()
                                            .mensaje("Error interno del servidor")
                                            .errores(List.of(ex.getMessage()))
                                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                            .timestamp(LocalDateTime.now())
                                            .path(request.getRequestURI())
                                        .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
    }    

}
/*
    Existen dos tipos d Excepciones:
        Checked exceptions: Java te obliga a capturarlas con try-catch o declararlas con throws.
        Unchecked exceptions: Java no te obliga a capturarlas ni declararlas.
    La diferencia no es que unas sean más graves que otras. La diferencia es cómo Java exige que el programador gestione su propagación.
    
      Característica                                | `extends Exception`                                              | `extends RuntimeException`                              |
      ¿Java exige capturar o declarar la excepción? | Sí                                                               | No                                                      |
      ¿Se puede propagar hasta otro método?         | Sí, declarando `throws`                                          | Sí, sin declararlo                                      |
      ¿Se puede capturar con `try-catch`?           | Sí                                                               | Sí                                                      |
      ¿Puede utilizarse en Spring Boot?             | Sí                                                               | Sí                                                      |
      ¿Cuándo resulta útil?                         | Cuando quieres exigir que el código llamador considere ese fallo | Cuando no quieres imponer esa obligación en cada método |

    1.- RuntimeException:
        1.1.- IllegalArgumentException 
            El argumento es incorrecto. Se utiliza cuando un método recibe un argumento que no es válido para la operación.
            El método recibió un argumento incompatible con las condiciones que acepta.
        1.2.- IllegalStateException
            El estado actual no permite la operación. Por ejemplo, intentar cerrar algo quey a esta cerrado.
        1.3.- NullPointerException
        
            Se intenta utilizar una referencia null de una manera que requiere un objeto.
        1.4.- IndexOutOfBoundsException
            Un índice está fuera de los límites permitidos.
        1.5.- NumberFormatException
            Una cadena no puede convertirse al formato numérico solicitado.
*/