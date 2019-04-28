package com.github.maxwell.base.thread.juc;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join 使用， 单机版的MapReduce
 * 模拟 MapReduce 统计单词数量
 */
@Slf4j
public class ForkJoinDemo {

    public static void main(String[] args) {
        String[] fc = {"Hello world", "Hello me", "Hello fork", "Hello join", "Fork join in world"};
        //创建ForkJoin线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        //创建任务
        MR mr = new MR(fc, 0, fc.length);
        //启动任务
        Map<String, Long> result = forkJoinPool.invoke(mr);
        result.forEach((k, v) -> {
            log.info("{}:{}", k, v);
        });
    }

    static class MR extends RecursiveTask<Map<String, Long>> {
        private String[] fc;
        private int start, end;

        public MR(String[] fc, int start, int end) {
            this.fc = fc;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) return cal(fc[start]);
            else {
                int mid = (start + end) >> 1;
                MR mr1 = new MR(fc, start, mid);
                MR mr2 = new MR(fc, mid, end);
                mr2.fork();
                //计算子任务
                return merge(mr1.compute(), mr2.join());
            }
        }

        //合并结果
        private Map<String, Long> merge(Map<String, Long> m1, Map<String, Long> m2) {
            Map<String, Long> result = Maps.newHashMap();
            result.putAll(m1);
            m2.forEach((k, v) -> {
                Long value = result.get(k);
                if (value != null) result.put(k, value.longValue() + v.longValue());
                else result.put(k, v);
            });
            return result;
        }

        //统计单词数量
        private Map<String, Long> cal(String line) {
            Map<String, Long> result = Maps.newHashMap();
            String[] words = line.split("\\s+");
            log.info("words size {} val {}", words.length, words);
            Arrays.stream(words).forEach(word -> {
                Long value = result.get(word);
                if (value != null) result.put(word, value.longValue() + 1);
                else result.put(word, 1l);
            });
            return result;
        }
    }
}
