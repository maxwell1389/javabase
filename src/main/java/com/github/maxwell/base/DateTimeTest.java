package com.github.maxwell.base;

import com.github.maxwell.base.main.ScoreType;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
public class DateTimeTest {
    private static Logger logger = LoggerFactory.getLogger(DateTimeTest.class);
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getStrDateTimeFmSec(Long longDateTime) {
        return (longDateTime == null || longDateTime.longValue() == 0) ? "" : LocalDateTime.ofInstant(Instant.ofEpochSecond(longDateTime), ZoneId.systemDefault()).format(DTF);
    }

    public static Long getLongDateNowMs() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String getStrDateTimeFm(Long longDateTime) {
        return (longDateTime == null || longDateTime.longValue() == 0) ? "" : LocalDateTime.ofInstant(Instant.ofEpochMilli(longDateTime), ZoneId.systemDefault()).format(DTF);
    }

    public static Long getLongDateTimeFm(String sdatetime) {
        return (sdatetime != null && !"".equals("")) ? 0l : LocalDateTime.parse(sdatetime, DTF).toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Long getLongDateNow() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Long getLongDateNow1() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Long getLongDateTimeMsFm(String sdatetime) {
        return (sdatetime != null && !"".equals("")) ? 0l : LocalDateTime.parse(sdatetime, DTF).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    public static Long getLongDateTimeMsFm1(String sdatetime) {
        return (StringUtils.isEmpty(sdatetime)) ? 0l : LocalDateTime.parse(sdatetime, DTF).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Integer toInteger(Long amount) {
        return BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(10000), 0, BigDecimal.ROUND_HALF_DOWN)
                .intValue();
    }

    public static boolean isDoubleStr(String str) {
        if (StringUtils.isEmpty(str)) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkDateFormatter(String strDatetime) {
        try {
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime.parse(strDatetime, DTF);
            return true;
        } catch (Exception e) {
            logger.error("checkDateFormatter error:{}", e);
            return false;
        }
    }

    public static boolean checkDateFormatter(String strDatetime, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(strDatetime, formatter);
            return true;
        } catch (Exception e) {
            logger.error("checkDateFormatter error:{}",e);
            return false;
        }
    }

    public static Long divLong(Long amount, Integer value) {
        logger.info("toInteger amount {}", amount);
        if (amount == null || amount.longValue() == 0) return 0L;
        return BigDecimal.valueOf(amount.longValue()).divide(BigDecimal.valueOf(value), 0, BigDecimal.ROUND_HALF_UP)
                .longValue();
    }

    public static Double divInteger(Integer firstV, Integer secondV, Integer scale) {
        if (firstV == null || secondV == null || firstV.intValue() == secondV.intValue()) return 0.0;
        return BigDecimal.valueOf(firstV.intValue()).divide(BigDecimal.valueOf(secondV.intValue()), scale, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    public static String getBeforeCurrDayFormat(int day, String formater) {
        Long ltimes = LocalDateTime.now().minusDays(day).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        DateTimeFormatter DTFDAY = DateTimeFormatter.ofPattern(formater);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ltimes), ZoneId.systemDefault()).format(DTFDAY);
    }

    public static Double multiRatio(Long longV, Double dratio, int scale) {
        if (longV == null || dratio == null) return 0.0;
        return BigDecimal.valueOf(longV.intValue()).multiply(BigDecimal.valueOf(dratio)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Long toLong(Long amount) {
        logger.info("toLong amount {}", amount);
        if (amount == null || amount.longValue() == 0) return Long.valueOf(0);
        return BigDecimal.valueOf(amount.longValue()).divide(BigDecimal.valueOf(100000), 0, BigDecimal.ROUND_DOWN)
                .longValue();
    }

    public static Long addLong(Long l1, Long l2) {
        l1 = (l1 == null) ? 0L : l1;
        l2 = (l2 == null) ? 0L : l2;
        return BigDecimal.valueOf(l1.longValue()).add(BigDecimal.valueOf(l2)).setScale(0, BigDecimal.ROUND_DOWN)
                .longValue();
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

//        log.info("time {}", getStrDateTimeFm(1560826860000L));
//        Long lg = 1560826860000L + 2*60*60*1000L;
//        log.info("lg time {}", getStrDateTimeFm(lg));

//        log.info("time {}", getLongDateTimeMsFm("2019-07-26 16:29:59"));
//        log.info("time {}", getLongDateTimeFm("2019-06-18 11:01:33"));
//        log.info("time {}", getLongDateTimeMsFm1("2019-07-18 16:00:00"));
//        log.info("v {}", 2%100);
//        log.info("b {}", new Integer("1000") == 1000);
//        log.info("b {}", new String("a") == "a");

//        log.info("v {}", checkDateFormatter("20190731", DateTimeFormatter.ofPattern("yyyyMMdd")));


//        log.info("time {}", LocalDateTime.parse("2029-12-30 23:59:59", DTF));
//        log.info("isDoubleStr {}", isDoubleStr("0.55s"));

//        log.info("long {}",getBeforeCurrDayFormat(0, "MMdd"));
//        log.info("dd {}", Integer.parseInt("0706") < Integer.parseInt("0707"));
//        Double d = 15.13;
//        log.info("int {}", d.intValue());
//        String utype = "user";
//        boolean b = !CarvenUserType.USER.utype.equals(utype) && !CarvenUserType.ANCHOR.utype.equals(utype);
//        log.info("int {}", multiRatio(1000000L, 0.00398, 2));
//            log.info("pow {}", Math.pow(10, 5));
//        log.info("v {}", (21469183000000L-17606023000000L)/100000);
//        log.info("v {}", (4706036000000L-2235716000000L)/100000);
//        log.info("v {}", (15860905720000l-14901685720000l)/100000);
//        log.info("v {}", addLong(15860905720000l, 14901685720000l));
//        log.info("v {}", ScoreType.TOTAL_SCORE);
//        Collections.list(ScoreType.values());
/*        for (ScoreType scoreType : ScoreType.values()) {
            log.info("l {} v {}",scoreType.desc(), scoreType.name());
        }*/

//        log.info("v {}", divInteger(67, 2, 0));

        log.info("v {}", getLongDateNowMs());

    }
}
