package com.devmind.performance.util;

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 29/03/16.
 */
public class StringUtils {

    /**
     * Converts a String to upper case
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase("")    = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     */
    public static String uppercase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.toUpperCase();
    }

    /**
     * Converts a String to lower case
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase("")    = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     */
    public static String lowercase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.toLowerCase();
    }

    /**
     * Capitalizes a String changing the first letter to uppercase. No other letters are changed.</p>
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * </pre>
     */
    public static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return new StringBuffer(str.length())
                .append(Character.toTitleCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }
}
