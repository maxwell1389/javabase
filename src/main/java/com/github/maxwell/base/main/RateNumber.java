package com.github.maxwell.base.main;

import com.github.maxwell.base.push.GsonTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RateNumber {
    private static Logger logger = LoggerFactory.getLogger(RateNumber.class);


    /**
     * 按概率返回数据
     *
     * @param separates
     * @param percents
     * @return
     */
    public static double produceRateRandomNumber(List<Double> separates, List<Integer> percents) {
        int totalPercent = 0;
        for (Integer p : percents) {
            if (p < 0 || p > 100) {
                throw new IllegalArgumentException("百分比必须在[0,100]之间");
            }
            totalPercent += p;
        }
        if (totalPercent != 100) {
            throw new IllegalArgumentException("百分比之和必须为100");
        }
        int rangeCount = separates.size();
        //构造分割的n段范围
        List<Range> ranges = new ArrayList<Range>();
        int scopeMax = 0;
        Range range;
        for (int i = 0; i < rangeCount; i++) {
            range = new Range();
            range.dval = separates.get(i);
            range.percent = percents.get(i);
            //片段占比，转换为[1,100]区间的数字
            range.percentScopeMin = scopeMax + 1;
            range.percentScopeMax = range.percentScopeMin + (range.percent - 1);
            scopeMax = range.percentScopeMax;
            ranges.add(range);
        }
        String sjson = GsonTools.gson.toJson(ranges);
        logger.info("ranges: {} ", sjson);
        List<Range> rds = (List<RateNumber.Range>)GsonTools.gson.fromJson(sjson, List.class);
        logger.info("rds {} a {}", rds.size(), rds.get(0));
        int randomInt = (int) (Math.random() * 100) + 1; //[1,100]
        for (Range record : ranges) {
            //判断使用哪个range返回数据
            if (record.percentScopeMin <= randomInt && randomInt <= record.percentScopeMax) {
                return record.dval;
            }
        }

        return -1;
    }

    public static class Range {
        public double dval;
        public int percent; //百分比
        public int percentScopeMin; //百分比转换为[1,100]的数字的最小值
        public int percentScopeMax; //百分比转换为[1,100]的数字的最大值
    }

    public static void main(String[] args) {
        List<Double> separates = Arrays.asList(1.2, 1.5, 1.6, 1.8, 2.0);
        List<Integer> percents = Arrays.asList(30, 25, 20, 15, 10);
        int i12 = 0, i15 = 0, i16 = 0, i18 = 0, i20 = 0;
        for (int i = 0; i < 100; i++) {
            double number = produceRateRandomNumber(separates, percents);
//            System.out.println(String.format("%.2f", number));
            if (number == 1.2) {
                i12 = i12 + 1;
            }
            if (number == 1.5) {
                i15 = i15 + 1;
            }
            if (number == 1.6) {
                i16 = i16 + 1;
            }
            if (number == 1.8) {
                i18 = i18 + 1;
            }
            if (number == 2.0) {
                i20 = i20 + 1;
            }
        }
        logger.info("1.2:{} 1.5:{} 1.6:{} 1.8:{} 2.0:{} ", i12, i15, i16, i18, i20);
    }
}