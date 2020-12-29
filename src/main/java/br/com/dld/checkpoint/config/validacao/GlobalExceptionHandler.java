package br.com.dld.checkpoint.config.validacao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author David Duarte
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroValidacaoDto> handle(MethodArgumentNotValidException exception) {
        List<ErroValidacaoDto> lista = new ArrayList();

        exception.getBindingResult().getFieldErrors().stream().map(erro -> lista.add(
                new ErroValidacaoDto(
                        erro.getField(),
                        messageSource.getMessage(erro, LocaleContextHolder.getLocale()))
        ));

        return lista;
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConversionFailedException.class)
    public String handleConflict(RuntimeException ex) {
        return ex.getMessage();
    }
}
