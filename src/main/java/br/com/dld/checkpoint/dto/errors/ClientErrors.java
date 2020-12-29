
package br.com.dld.checkpoint.dto.errors;

/**
 *
 * @author David Duarte
 */
public class ClientErrors {

    private final String mensagem;
    
    public ClientErrors(String message) {
        this.mensagem = message;
    }
    
    public ClientErrors(Exception exception) {
        this.mensagem = exception.getMessage();
    }
    
    public ClientErrors(RuntimeException exception) {
        this.mensagem = exception.getMessage();
    }

    public String getMensagem() {
        return mensagem;
    }
}
