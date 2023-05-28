package com.pucminas.cbea.global.util;

import java.util.regex.Pattern;

public class EmailValidatorUtil {

    public static final String EMAIL_REGEXP = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$";

    public static boolean isValid(String email) {
        Pattern pattern = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-_]+\\.)+[\\w]+[\\w]$");
        return pattern.matcher(email).matches();
    }
}