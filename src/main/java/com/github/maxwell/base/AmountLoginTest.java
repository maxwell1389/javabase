package com.github.maxwell.base;


import com.github.maxwell.base.main.WuFuType;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AmountLoginTest {
    private static Logger logger = LoggerFactory.getLogger(AmountLoginTest.class);

    private static Integer nextLotteryAmount(Long startTime, Long endTime, boolean isExpired) {
        List<Integer> lotteryConfigs =
                Splitter.on(",").omitEmptyStrings().trimResults().splitToList("90,180,450,540,810,1080,1620,1800")
                        .stream().map(Integer::parseInt).collect(Collectors.toList());
        Integer benchSecondsToNextLevel = 90;
        Integer nextLotteryAmount = lotteryConfigs.get(0);
        int gameSpendSeonds;
        RangeMap<Integer, Integer> rangeMap = TreeRangeMap.create();
        Map<Integer, Integer> mapIndex = Maps.newHashMap();
        for (int i = 0; i < lotteryConfigs.size(); i++) {
            Integer lowerBound = 0;
            if (i > 0) lowerBound = lotteryConfigs.get(i - 1);
            Integer higherBound = lotteryConfigs.get(i);
            if (i == lotteryConfigs.size() - 1) {
                higherBound = Integer.MAX_VALUE;
            }
            Range<Integer> range = Range.closedOpen(lowerBound, higherBound);
            rangeMap.put(range, lotteryConfigs.get(i));
            mapIndex.put(lotteryConfigs.get(i), i);
        }

        rangeMap.asMapOfRanges().entrySet().forEach(a -> System.out.println(a.getValue()));

        logger.info("rangMap {}, val {}", rangeMap.toString(), rangeMap.get(460));
        if (startTime != null && startTime > 0) {
            gameSpendSeonds = (int) ((endTime - startTime) / 1000);
        } else {
            gameSpendSeonds = 180;
        }
        if (gameSpendSeonds > 0) {
            Integer key = 1080 / gameSpendSeonds * benchSecondsToNextLevel;
            logger.info("key {} ", key);
            nextLotteryAmount = rangeMap.get(key);
        }
        logger.info("nextLotteryAmount amount {} spend {} next {} ", 10, gameSpendSeonds, nextLotteryAmount);

        if (isExpired) {
            if (nextLotteryAmount == lotteryConfigs.get(0)) return nextLotteryAmount;
            int index = mapIndex.get(nextLotteryAmount) - 1;
            int returnAmount = mapIndex.entrySet().stream().filter(a -> a.getValue() == index).collect(Collectors.toList()).get(0).getKey();
            logger.info("returnAmount {}", returnAmount);
            return returnAmount;
        }
        return nextLotteryAmount;
    }

    private static void ramAmount(Integer num, Map<Integer, Object> mapValIndex, Map<Integer, Object> mapSelected) {
        List<Integer> lotteryConfigs =
                Splitter.on(",").omitEmptyStrings().trimResults().splitToList("888,1888,3888,5888,8888")
                        .stream().map(Integer::parseInt).collect(Collectors.toList());
        RangeMap<Integer, Integer> rangeMap = TreeRangeMap.create();
        for (int i = 0; i < lotteryConfigs.size(); i++) {
            Integer lowerBound = 0;
            if (i > 0) lowerBound = lotteryConfigs.get(i - 1);
            Integer higherBound = lotteryConfigs.get(i);
            Range<Integer> range = Range.closedOpen(lowerBound, higherBound);
            if (i > 0)
                rangeMap.put(range, lotteryConfigs.get(i - 1));
            else rangeMap.put(range, 0);
            mapValIndex.put(lotteryConfigs.get(i), i);
        }
        log.info("s {}, {}", rangeMap.get(num), mapValIndex.get(rangeMap.get(num)));
        if (num <= 888) {
            mapSelected.put(888, mapValIndex.get(num));
            return;
        }
        rangeMap.asMapOfRanges().entrySet().forEach(a -> System.out.println(a.getKey() + ":" + a.getValue()));
        if (mapValIndex.containsKey(rangeMap.get(num))) {
            int maxSelectIndex = (Integer) mapValIndex.get(rangeMap.get(num));
            if (maxSelectIndex >= 0) {
                for (int i = 0; i <= maxSelectIndex; i++) {
                    mapSelected.put(lotteryConfigs.get(i), i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Object> mapSelected = Maps.newHashMap();
        Map<Integer, Object> mapValIndex = Maps.newHashMap();
        ramAmount(8887, mapValIndex, mapSelected);
//        log.info("dd {} {}", WuFuType.valueOf("CS").getDesc(), WuFuType.valueOf("CS").getNum());
        Map<Integer, Object> mapNum = Maps.newHashMap();
        Arrays.stream(WuFuType.values()).forEach(record -> {
            if (mapSelected.containsKey(record.getNum())) {
                log.info("select {} {}", record.getNum(), record.getDesc());
            }
            mapNum.put(record.getNum(), record.getDesc());
        });
        log.info("aa {} {}", 8887%8888, 8887/8888);
//        log.info("ss {}", mapNum.get(1888));
    }

}
