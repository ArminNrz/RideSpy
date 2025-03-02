package com.example.ridespy.domain.util;

import java.time.Instant;

public abstract class DateTimeUtils {

    public static long currentTimeMillis() {
        return Instant.now().getEpochSecond();
    }
}
