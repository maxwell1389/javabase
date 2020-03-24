package com.github.maxwell.base.thread.synchronizeed;

/**
 * 三个线程ABC，按顺序打印数字，从0开始，每个线程打印5次，一直到75
 * 使用Synchronized实现
 *
 */
public class PrintNum_Synch {
    private static volatile int num = 0;

    public static class ThreadPrinter implements Runnable {
        private String name;
        private Object prev;
        private Object self;


        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 0;
            while (count < 5) {// 多线程并发，不能用if，必须使用while循环
                synchronized (prev) { // 先获取 prev 锁
                    synchronized (self) {// 再获取 self 锁
                        System.out.print(name + count + ":");// 打印
                        for (int i = 0; i < 5; i++) {
                            System.out.print(num + " ");
                            num ++;
                        }
                        count++;
                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (count == 4) {// 如果count==4,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
                            prev.notifyAll();
                        } else {
                            prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A", c, a);
        ThreadPrinter pb = new ThreadPrinter("B", a, b);
        ThreadPrinter pc = new ThreadPrinter("C", b, c);

        new Thread(pa).start();
        Thread.sleep(10);// 保证初始ABC的启动顺序
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }
}
