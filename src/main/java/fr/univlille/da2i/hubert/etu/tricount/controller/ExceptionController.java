package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.utils.UrlUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public String exceptionHandler(final Exception exception, final HttpServletRequest request) {
        String message = exception.getMessage() == null ? "Unexpected exception occured!" : exception.getMessage();
        return UrlUtils.buildRedirectUrlWithError(request.getHeader("Referer"), message);
    }

    @ExceptionHandler(BindException.class)
    public String referExceptions(final BindException exception, final HttpServletRequest request) {
        return UrlUtils.buildRedirectUrlWithError(request.getHeader("Referer"), bindExceptionToString(exception));
    }

    public static String bindExceptionToString(final BindException exception) {
        final StringJoiner sj = new StringJoiner("\n");
        exception.getBindingResult().getAllErrors().forEach((error) ->
                sj.add(((FieldError) error).getField() + ": " + error.getDefaultMessage()));
        return sj.toString();
    }

}
