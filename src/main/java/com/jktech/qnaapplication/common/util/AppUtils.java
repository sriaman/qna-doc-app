package com.jktech.qnaapplication.common.util;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppUtils {

    public static String getCurrentTimestamp() {
        return LocalDateTime.now().toString();
    }
}
