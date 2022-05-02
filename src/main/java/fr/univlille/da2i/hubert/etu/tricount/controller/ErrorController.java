package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.utils.UrlUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public String errorPage(final Exception exception, final HttpServletRequest servletRequest) {
        final String referer = servletRequest.getHeader("referer");
        if (exception != null && referer != null) {
            final String message;

            if (exception instanceof BindException) {
                final BindException bindException = (BindException) exception;
                message = bindExceptionToString(bindException);
            } else {
                message = exception.getMessage();
            }
            return UrlUtils.buildRedirectUrlWithError(referer, message);
        }
        return "ErrorPage";
    }

    public static String bindExceptionToString(final BindException bindException) {
        final StringJoiner sj = new StringJoiner("\n");
        for (final ObjectError objectError : bindException.getAllErrors())
            sj.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
        return sj.toString();
    }

}
