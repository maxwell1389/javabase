package com.github.maxwell.base.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock使用例子
 */
@Slf4j
public class StampedLockDemo {
    private int balance = 0;
    private StampedLock lock = new StampedLock();

    public StampedLockDemo() {
        this.balance = 0;
    }

    private void conditionReadWrite(int value) {
        long stamp = lock.readLock();
        log.info("conditionReadWrite stamp {}", stamp);
        while (balance > 0) {
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            log.info("conditionReadWrite writeStamp {}", stamp);
            if (writeStamp != 0) { //成功转换成写锁
                stamp = writeStamp;
                balance += value;
                break;
            } else {
                //没有转换成写锁，这里需先释放读锁，然后再拿到写
                lock.unlock(stamp);
                stamp = lock.writeLock();
            }
        }
        lock.unlock(stamp);
    }

    private void optimisticRead() {
        long stammp = lock.tryOptimisticRead();
        log.info("optimisticRead stamp {}", stammp);
        int c = balance;
        //这里可能会出现了写操作，因此要进行判断
        if (!lock.validate(stammp)) {
            //要重新读取
            stammp = lock.readLock();
            try {
                c = balance;
            } finally {
                lock.unlock(stammp);
            }
        }
        log.info("optimisticRead {}", c);
    }

    private void read() {
        long stamp = lock.readLock();
        log.info("read stamp {}", stamp);
        lock.tryOptimisticRead();
        int c = balance;
        log.info("read {}", c);
        lock.unlock(stamp);
    }

    private void write(int value) {
        long stamp = lock.writeLock();
        log.info("write stamp {} value {}", stamp, value);
        balance += value;
        lock.unlock(stamp);
    }

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        new Thread(() -> {
            while (true) {
                stampedLockDemo.read();
                stampedLockDemo.optimisticRead();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                stampedLockDemo.write(2);
                stampedLockDemo.conditionReadWrite(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
