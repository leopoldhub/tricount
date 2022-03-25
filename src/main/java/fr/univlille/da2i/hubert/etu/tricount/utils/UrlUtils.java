package fr.univlille.da2i.hubert.etu.tricount.utils;

public class UrlUtils {

    public static String buildRedirectUrl(final String redirect) {
        return String.format("redirect:%s", redirect);
    }

    public static String buildRedirectUrlWithError(final String redirect, final String error) {
        String redirectUrl = UrlUtils.buildRedirectUrl(redirect).replaceAll("((&)?error=([^&]*))", "");
        String separator = redirectUrl.contains("?")?!redirectUrl.endsWith("?")?"&":"":"?";
        return redirectUrl + separator + "error=" + error;
    }

}
