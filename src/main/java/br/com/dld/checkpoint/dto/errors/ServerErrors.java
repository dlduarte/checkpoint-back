
package br.com.dld.checkpoint.dto.errors;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Duarte
 */
public class ServerErrors {

    private final String mensagem;
    private final String tipo;
    private final String stackTrace;
    
    public ServerErrors(Exception exception) {
        this.mensagem = exception.getMessage();
        this.tipo = exception.getClass().getName();
        this.stackTrace = Arrays.toString(exception.getStackTrace());
        
        try {
            Logger.getLogger(ServerErrors.class.getName()).log(Level.SEVERE, null, exception);
        } catch (Exception e) {
        }
    }
    
    public ServerErrors(RuntimeException exception) {
        this.mensagem = exception.getMessage();
        this.tipo = exception.getClass().getName();
        this.stackTrace = Arrays.toString(exception.getStackTrace());
        
        try {
            Logger.getLogger(ServerErrors.class.getName()).log(Level.SEVERE, null, exception);
        } catch (Exception e) {
        }
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
