package br.com.dld.checkpoint.models.dtos.errors;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author David Duarte
 */
public class ServerErrors {

    private final String message;
    private final String className;
    private final String stackTrace;

    public static ResponseEntity<?> build(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServerErrors(exception));
    }

    public static ResponseEntity<?> build(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServerErrors(exception));
    }

    public ServerErrors(Exception exception) {
        this.message = exception.getMessage();
        this.className = exception.getClass().getName();
        this.stackTrace = Arrays.toString(exception.getStackTrace());

        try {
            Logger.getLogger(ServerErrors.class.getName()).log(Level.SEVERE, null, exception);
        } catch (Exception e) {
        }
    }

    public ServerErrors(RuntimeException exception) {
        this.message = exception.getMessage();
        this.className = exception.getClass().getName();
        this.stackTrace = Arrays.toString(exception.getStackTrace());

        try {
            Logger.getLogger(ServerErrors.class.getName()).log(Level.SEVERE, null, exception);
        } catch (Exception e) {
        }
    }

    public String getMessage() {
        return message;
    }

    public String getClassName() {
        return className;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
