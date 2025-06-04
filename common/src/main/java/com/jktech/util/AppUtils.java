package com.jktech.util;

import java.time.LocalDateTime;

public class AppUtils {

    public static String getCurrentTimestamp() {
        return LocalDateTime.now().toString();
    }
}
