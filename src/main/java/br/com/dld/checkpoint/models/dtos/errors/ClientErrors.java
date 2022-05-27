package br.com.dld.checkpoint.models.dtos.errors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author David Duarte
 */
public class ClientErrors {

    private final List<String> messages = new ArrayList();

    public static ResponseEntity build(List<String> messages) {
        return ResponseEntity
                .badRequest()
                .body(new ClientErrors(messages));
    }

    public static ResponseEntity build(String message) {
        return ResponseEntity
                .badRequest()
                .body(new ClientErrors(message));
    }

    public static ResponseEntity build(Exception exception) {
        return ResponseEntity
                .badRequest()
                .body(new ClientErrors(exception));
    }

    public static ResponseEntity build(RuntimeException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ClientErrors(exception));
    }

    public ClientErrors(List<String> message) {
        this.messages.addAll(message);
    }

    public ClientErrors(String message) {
        this.messages.add(message);
    }

    public ClientErrors(Exception exception) {
        this.messages.add(exception.getMessage());
    }

    public ClientErrors(RuntimeException exception) {
        this.messages.add(exception.getMessage());
    }

    public List<String> getMessages() {
        return messages;
    }
}
