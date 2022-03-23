package fr.univlille.da2i.hubert.etu.tricount.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

@ControllerAdvice//TODO: catch all exceptions (1 handler for user exceptions and 1 for server)
public class BindExceptionAdviceController {

    @ExceptionHandler(BindException.class)
    public String bindingException(final BindException exception, final HttpServletRequest request) {
        final StringJoiner sj = new StringJoiner("\n");
        exception.getBindingResult().getAllErrors().forEach((error) ->
                sj.add(((FieldError) error).getField()+": "+error.getDefaultMessage()));
        final String referer = request.getHeader("Referer");
        return "redirect:/"+referer+"?error="+ sj;
    }

}
