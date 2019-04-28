package com.github.maxwell.base;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
public class DateTimeTest {
    private static Logger logger = LoggerFactory.getLogger(DateTimeTest.class);

    public static void main(String[] args) {
/*        LocalDate stdate = LocalDate.of(2019, 3, 28);
        LocalDate enddate = LocalDate.of(2019, 4, 1);
        logger.info("days {}", ChronoUnit.DAYS.between(stdate, enddate));
        Instant inst1 = Instant.now();
        logger.info("inst1 {}",inst1);
        Instant inst2 = inst1.plus(Duration.ofSeconds(10));
        logger.info("inst2 {}",inst2);
        logger.info("secords {}", Duration.between(inst1, inst2).getSeconds());
        logger.info("millis {}", Duration.between(inst1, inst2).toMillis());*/

/*        Long l1 = 1416052839123L;
        l1 += 0;
        logger.info("ll {}", l1+l1);*/
        String[] fc = {"Hello world", "Hello me", "Hello fork", "Hello join", "Fork join in world"};
        String[] words = fc[0].split("\\s+");
        log.info("words size {} vl {}", words, words.length);
    }
}
