package com.github.maxwell.base.thread.autoworker;

import com.github.maxwell.base.thread.worker.PackageChannel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// 挖矿人
public class Worker extends Thread {
    private final MiningChannel channel;
    private static volatile Boolean flag = true;

    public Worker(String name, MiningChannel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (flag) {
            flag = channel.take().execute();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
