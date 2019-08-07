package com.github.maxwell.base.thread.autoworker;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

// 挖矿流水线
public class MiningChannel {
    private final static int MAX_PACKAGE_NUM = 1;
    private final Mining[] miningQueue;

    private final Worker[] workerPool;

    public MiningChannel(int workers) {
        this.miningQueue = new Mining[MAX_PACKAGE_NUM];
        this.workerPool = new Worker[workers];
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new Worker("WorkerMining-" + i, this);
        }
    }

    public void startWorker() {
        Arrays.asList(workerPool).forEach(Worker::start);
    }

    public synchronized void put(Mining mining) {
        this.miningQueue[0] = mining;
    }

    public synchronized Mining take() {
        Mining request = this.miningQueue[0];
        return request;
    }

}
