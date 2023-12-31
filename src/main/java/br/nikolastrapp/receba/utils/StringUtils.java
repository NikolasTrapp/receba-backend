package br.nikolastrapp.receba.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@UtilityClass
public class StringUtils {

    public static boolean isEmpty(String s) {
        return isNull(s) || s.isBlank();
    }

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static String format(String template, Object... args) {
        return String.format(template, args);
    }

    public static boolean isValidEmail(String email) {
        var emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        var pattern = Pattern.compile(emailRegex);
        var matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
