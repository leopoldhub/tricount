package fr.univlille.da2i.hubert.etu.tricount.utils;

public class UrlUtils {

    public static String buildRedirectUrl(final String redirect) {
        return String.format("redirect:%s", redirect);
    }

    public static String buildRedirectUrl(final String redirect, final String error) {
        return UrlUtils.buildRedirectUrl(redirect) + String.format("?error=%s", error);
    }

}
