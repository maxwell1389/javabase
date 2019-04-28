package com.github.maxwell.base.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join 并行计算框架计算斐波那契数列
 */
@Slf4j
public class ForkJoinDemo1 {

    public static void main(String[] args) {
        //创建分治任务线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        //创建分治任务
        Fibonacci fibonacci = new Fibonacci(30);
        //启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);
        log.info("result {}", result);
    }

    //递归任务
    static class Fibonacci extends RecursiveTask<Integer> {
        int n = 0;

        public Fibonacci(int n) {
            log.info("n {}", n);
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            //创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            //等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }

}
