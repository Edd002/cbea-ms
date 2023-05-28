package com.pucminas.cbea.global.util;

import org.apache.commons.lang3.StringUtils;

import javax.swing.text.MaskFormatter;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.Optional;

public class TextUtils {
    public static String coalesce(String text) {
        return Optional.ofNullable(text).orElse("");
    }

    public static String coalesce(String text, String alternative) {
        return Optional.ofNullable(text).orElse(alternative);
    }

    public static String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String applyMask(String text, String mask) throws ParseException {
        MaskFormatter mf = new MaskFormatter(mask);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(text);
    }

    public static String removeNonAlphanumeric(String str) {
        return str != null ? Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^a-zA-Z0-9-\\s-_]", "").trim() : "";
    }

    public static boolean equalsIgnoreAccentsSpacesCase(String str1, String str2) {
        return StringUtils.normalizeSpace(StringUtils.stripAccents(str1)).equalsIgnoreCase(StringUtils.normalizeSpace(StringUtils.stripAccents(str2)));
    }
}
