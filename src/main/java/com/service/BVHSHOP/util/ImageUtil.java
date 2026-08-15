package com.service.BVHSHOP.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/15/2026 7:38 AM
 */
public class ImageUtil {
    public static String withCurrentTime(String name) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return currentTime + "_" + name;
    }
}
