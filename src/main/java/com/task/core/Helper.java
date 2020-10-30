package com.task.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Mikhail Terletskyi
 * @Create 10/29/2020
 */

public class Helper {
    public static final String CREATE_CONTROLLER = "Create Controller";
    public static final String UPDATE_CONTROLLER = "Update Controller";

    public static boolean isEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static int getTimeLength(LocalDate start, ChronoUnit chronoUnit) {
        return (int) chronoUnit.between(start, LocalDate.now());
    }

    public static boolean stringHasDigits(String s) {
        return !(s.trim().toLowerCase().replaceAll("[^0-9]", "").length() == 0);
    }

    public static boolean stringHasLetters(String s) {
        return  !(s.trim().toLowerCase().replaceAll("[^a-z]", "").length() == 0);
    }
}