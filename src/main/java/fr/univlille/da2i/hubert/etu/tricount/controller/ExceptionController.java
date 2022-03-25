package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.utils.UrlUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    @RequestMapping
    public String exceptionHandler(final Exception exception) throws Exception {
        throw exception;
    }

    /*@ExceptionHandler({BindException.class, BadRequestException.class, ForbiddenException.class, AuthenticationCredentialsNotFoundException.class})
    public String referExceptions(final BindException exception, final HttpServletRequest request) {
        return UrlUtils.buildRedirectUrl(request.getHeader("Referer"), bindExceptionToString(exception));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(final UnauthorizedException exception) {
        return UrlUtils.buildRedirectUrl("/login", exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public String unexpectedException(final Exception exception, final HttpServletResponse servletResponse, final HttpServletRequest request) {
        String response;

        switch (HttpStatus.valueOf(servletResponse.getStatus())) {
            case FORBIDDEN:
                response = UrlUtils.buildRedirectUrl(request.getHeader("Referer"), exception.getMessage());
                break;
            case UNAUTHORIZED:
                response = UrlUtils.buildRedirectUrl("/login", exception.getMessage());
                break;
            default:
                response = UrlUtils.buildRedirectUrl("/unexpected", exception.getMessage());
                break;
        }
        return response;
    }

    public static String bindExceptionToString(final BindException exception) {
        final StringJoiner sj = new StringJoiner("\n");
        exception.getBindingResult().getAllErrors().forEach((error) ->
                sj.add(((FieldError) error).getField() + ": " + error.getDefaultMessage()));
        return sj.toString();
    }*/

}
