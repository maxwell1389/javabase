package com.github.maxwell.base.delaytask;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * DelayQueue是一个阻塞队列的实现，它的队列元素必须是Delayed的子类
 */
@Slf4j
public class DelayQueueMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelayQueueMain.class);

    public static void main(String[] args) throws Exception {
        DelayQueue<OrderMessage> queue = new DelayQueue<>();
        // 默认延迟5秒
        OrderMessage message = new OrderMessage("ORDER_ID_10086");
        queue.add(message);
        // 延迟6秒
        message = new OrderMessage("ORDER_ID_10087", 10);
        queue.add(message);
        // 延迟10秒
        message = new OrderMessage("ORDER_ID_10088", 6);
        queue.add(message);
        ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
            Thread thread = new Thread(r);
            thread.setName("DelayWorker");
            thread.setDaemon(true);
            return thread;
        });
        LOGGER.info("开始执行调度线程...");
        executorService.execute(() -> {
            while (true) {
                try {
                    OrderMessage task = queue.take();
                    LOGGER.info("延迟处理订单消息,{}", task.getDescription());
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static class OrderMessage implements Delayed {

        private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        /**
         * 默认延迟5000毫秒
         */
        private static final long DELAY_MS = 1000L * 5;

        /**
         * 订单ID
         */
        private final String orderId;

        /**
         * 创建时间戳
         */
        private final long timestamp;

        /**
         * 过期时间
         */
        private final long expire;

        /**
         * 描述
         */
        private final String description;

        public OrderMessage(String orderId, long expireSeconds) {
            this.orderId = orderId;
            this.timestamp = System.currentTimeMillis();
            this.expire = this.timestamp + expireSeconds * 1000L;
            this.description = String.format("订单[%s]-创建时间为:%s,超时时间为:%s", orderId,
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).format(F),
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(expire), ZoneId.systemDefault()).format(F));
        }

        public OrderMessage(String orderId) {
            this.orderId = orderId;
            this.timestamp = System.currentTimeMillis();
            this.expire = this.timestamp + DELAY_MS;
            this.description = String.format("订单[%s]-创建时间为:%s,超时时间为:%s", orderId,
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).format(F),
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(expire), ZoneId.systemDefault()).format(F));
        }

        public String getOrderId() {
            return orderId;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public long getExpire() {
            return expire;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
