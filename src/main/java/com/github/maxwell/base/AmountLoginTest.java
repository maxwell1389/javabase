package com.github.maxwell.base;


import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        if (gameSpendSeonds > 0){
            Integer key = 1080 / gameSpendSeonds * benchSecondsToNextLevel;
            logger.info("key {} ", key);
            nextLotteryAmount = rangeMap.get(key);
        }
        logger.info("nextLotteryAmount amount {} spend {} next {} ", 10, gameSpendSeonds, nextLotteryAmount);

        if(isExpired){
            if(nextLotteryAmount == lotteryConfigs.get(0)) return nextLotteryAmount;
            int index = mapIndex.get(nextLotteryAmount) - 1;
            int returnAmount = mapIndex.entrySet().stream().filter(a -> a.getValue() == index).collect(Collectors.toList()).get(0).getKey();
            logger.info("returnAmount {}", returnAmount);
            return returnAmount;
        }
        return nextLotteryAmount;
    }

    public static void main(String[] args) throws InterruptedException {
//        Long beforeTime = System.currentTimeMillis() - 1000*7; //前一秒
        Long beforeTime = System.currentTimeMillis() - 1000*60*3; //前三分钟
        Long currTime = System.currentTimeMillis(); //当前时间
        int nextLotteryAmount = nextLotteryAmount(beforeTime, currTime,  false);
        logger.info("nextLotteryAmount amount {} next {} ", 10, nextLotteryAmount);
        System.out.println(Long.valueOf("1553679101492")-Long.valueOf("1553679093995"));
    }

}
