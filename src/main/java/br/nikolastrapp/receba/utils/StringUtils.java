package br.nikolastrapp.receba.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

import static java.util.Objects.isNull;

@UtilityClass
public class StringUtils {

    public static boolean isEmpty(String s) {
        return isNull(s) || s.isBlank();
    }

}
