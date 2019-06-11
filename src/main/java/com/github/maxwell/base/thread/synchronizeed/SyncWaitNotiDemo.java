package com.github.maxwell.base.thread.synchronizeed;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Syncronized使用 用“等待-通知”机制优化循环等待
 */
@Slf4j
public class SyncWaitNotiDemo {

    /**
     * 账户类
     */
    static class Account{
        private Integer balance;

        public Account(Integer balance) {
            this.balance = balance;
        }

        //转账方法
        public void transactionToTarget(Integer money, Account target) {
            Allocator.getInstance().apply(this, target);
            log.info("{} trans...",Thread.currentThread().getName());
            this.balance -= money;
            target.setBalance(target.getBalance() + money);
            Allocator.getInstance().release(this, target);
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

    }

    /**
     * 单例锁类
     */
    static class Allocator {
        private Allocator(){}
        private List<Account> locks = Lists.newArrayList();

        //加锁
        public synchronized void apply(Account from, Account to) {
            log.info("{} start wait...",Thread.currentThread().getName());
            while (locks.contains(from) || locks.contains(to)) {
                try {
                    log.info("{} wait...",Thread.currentThread().getName());
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            locks.add(from);
            locks.add(to);
        }

        //释放
        public synchronized void release(Account from, Account to) {
            locks.remove(from);
            locks.remove(to);
            this.notifyAll();
            log.info("{} release", Thread.currentThread().getName());
        }

        public static Allocator getInstance() {
            return AllocatorSingle.allocator;
        }

        static class AllocatorSingle {
            public static Allocator allocator = new Allocator();
        }
    }

    public static void main(String[] args) {
        Account src = new Account(10000);
        Account target = new Account(10000);
        int counts = 3;
        CountDownLatch latch = new CountDownLatch(counts);
        for (int i = 0; i < counts; i++) {
            new Thread(()->{
                src.transactionToTarget(1, target);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("src balance {}, target {}", src.getBalance(), target.getBalance());
    }

}
