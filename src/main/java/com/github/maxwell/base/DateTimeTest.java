package com.github.maxwell.base;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class DateTimeTest {
    private static Logger logger = LoggerFactory.getLogger(DateTimeTest.class);
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ms");

    public static String getStrDateTimeFm(Long longDateTime) {
        return (longDateTime == null || longDateTime.longValue() == 0) ? "" : LocalDateTime.ofInstant(Instant.ofEpochMilli(longDateTime), ZoneId.systemDefault()).format(DTF);
    }
    public static Long getLongDateTimeFm(String sdatetime) {
        return (sdatetime !=null && !"".equals("")) ? 0l : LocalDateTime.parse(sdatetime, DTF).toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Long getLongDateNow() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Long getLongDateNow1() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    public static Integer toInteger(Long amount) {
        return BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(10000), 0, BigDecimal.ROUND_HALF_DOWN)
                .intValue();
    }

    public static boolean checkDateFormatter(String strDatetime) {
        try {
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime.parse(strDatetime, DTF);
            return true;
        } catch (Exception e) {
            logger.error("checkDateFormatter error:{}",e);
            return false;
        }
    }

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
/*        String[] fc = {"Hello world", "Hello me", "Hello fork", "Hello join", "Fork join in world"};
        String[] words = fc[0].split("\\s+");
        log.info("words size {} vl {}", words, words.length);*/
/*        List<Long> longs = Lists.newArrayList();
        longs.add((BigInteger.valueOf(0l).longValue()));
        longs.add(20000l);
        Integer integer = toInteger(longs.get(0));
        log.info("ii {}", integer);*/
//        DateTimeFormatter sdtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
/*        long seconds = LocalDateTime.parse("2019-05-13 20:50:59", sdtf).toEpochSecond(ZoneOffset.of("+8"));
        log.info("second long:{}", seconds);*/
//        Instant instant = Instant.ofEpochSecond(1531554716848L);
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("+8"));
//        log.info("time:{}", localDateTime.format(sdtf));
      /*  log.info("now:{}",LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
//        log.info("checkDateFormatter {}",checkDateFormatter("2019-05-13 20:55"));
*/
//        log.info("long:{} {} {}",30 * 60 * 1000, System.currentTimeMillis()-1558419088875L, (System.currentTimeMillis()-1558419088875L)<30 * 60 * 1000);
        long now = getLongDateNow1();
        log.info("now {}", now);
        long now2 = getLongDateTimeFm("2019-06-06 18:43:00");
        log.info("now1 {}", System.currentTimeMillis());
        log.info("now2 {}", now2);
//        1559616809363
        log.info("time str {}", getStrDateTimeFm(now));
        log.info("time2 str {}", getStrDateTimeFm(now2));
    }
}
