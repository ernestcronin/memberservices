package com.biz.memberservices.entity.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;


public class DateTestHelper {

    public static final Instant past = Instant.now()
            .minus(Duration.ofDays(100 * 365));

    public static final Instant current = Instant.now();

    public static Instant generateRandomInstant() {

        long startSeconds = past.getEpochSecond();
        long endSeconds = current.getEpochSecond();
        long random = ThreadLocalRandom.current()
                .nextLong(startSeconds, endSeconds);
        return Instant.ofEpochSecond(random);
    }

    @Test
    void shouldReturnRandomInstant() {

        Instant s = generateRandomInstant();
        assertThat(s).isBetween(past, current);
    }
}

