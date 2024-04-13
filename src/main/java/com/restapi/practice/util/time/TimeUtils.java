package com.restapi.practice.util.time;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Component
public final class TimeUtils {
    public final String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }
}
