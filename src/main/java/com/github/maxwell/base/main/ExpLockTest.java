package com.github.maxwell.base.main;

public class ExpLockTest {
    public static void main(String[] args) {
        ExpLock lock = new ExpLock("abc", "def");
        try {
            lock.lock(true);
        } finally {
            lock.unlock();
        }
    }
}
