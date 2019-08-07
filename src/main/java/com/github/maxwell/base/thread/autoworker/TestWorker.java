package com.github.maxwell.base.thread.autoworker;


public class TestWorker {
    public static void main(String[] args) {
        // 新建 1 个工人
        final MiningChannel channel = new MiningChannel(1);
        //添加挖矿数量
        Mining mining = new Mining();
        mining.setKey("123");
        mining.setAmount(1200);
        channel.put(mining);
        // 开始工作
        channel.startWorker();
    }
}
