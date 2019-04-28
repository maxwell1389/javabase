package com.github.maxwell.base.thread.future;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Future CompletableFuture 使用
 */
@Slf4j
public class Shop {
    private String name;
//    Executor executor;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }

    public String getPriceNew(String product) {
        Random random = new Random();
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return this.name + ":" + calculatePrice(product) + ":" + code;
    }

    //同步
    public Double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return Math.random();
    }

    public void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    //这个方法将在顺序查询,并行查询和异步查询中实现不同逻辑
    public List<String> findPrice(String product) {
        return shops.stream().map(shop -> shop.getName() + shop.getPrice(product)).collect(Collectors.toList());
    }

    //并行查询
    public List<String> findPrice1(String product) {
        return shops.parallelStream().map(shop -> shop.getName() + shop.getPrice(product)).collect(Collectors.toList());
    }

    //异步
    public List<CompletableFuture<String>> findPrice2(String product) {
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + shop.getPrice(product))).collect(Collectors.toList());
    }

    //异步join
    public List<String> findPrice3(String product) {
        List<CompletableFuture<String>> collect = shops.stream().
                map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + shop.getPrice(product)))
                .collect(Collectors.toList());
        return collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    //异步Executor
    public List<String> findPrice4(String product, Executor executorm) {
        List<CompletableFuture<String>> collect = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + shop.getPrice(product), executorm))
                .collect(Collectors.toList());
        return collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    //折扣
    public List<String> findPriceNew(String product) {
        return shops.parallelStream().map(shop -> shop.getPriceNew(product)) //根据商品名得到商品信息:ShopName:price:Discount
                .map(Quote::parse)                                   //根据商品信息,将信息封装到Quote返回
                .map(Discount::applyDiscount)                        //将Quote传入,并根据原始价格和折扣力度获取最新价格,返回shopName+newPrice
                .collect(Collectors.toList());                       //收集到List中
    }

    //异步Executor
    public List<String> findPriceNew1(String product, Executor executorm) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                //以异步方式取得每个shop中指定产品的原始价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceNew(product), executorm))
                //在quote对象存在的时候,对其返回的值进行转换
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异步任务构造期望的Future,申请折扣
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executorm)))
                .collect(Collectors.toList());
        return priceFutures.stream()
                //等待所有Future结束,并收集到List中
                .map(CompletableFuture::join).collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPriceNew2(String product, Executor executor1) {
        return shops.stream()
                //以异步方式取得每个shop中指定产品的原始价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceNew(product), executor1))
                //在quote对象存在的时候,对其返回的值进行转换
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异步任务构造期望的Future,申请折扣
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor1)));
    }

    //商家列表
    List<Shop> shops = Lists.newArrayList();

    public void initData() {
        shops = Arrays.asList(new Shop("huawei"), new Shop("B")
                , new Shop("C"), new Shop("D")
                , new Shop("E"), new Shop("F")
                , new Shop("G"), new Shop("H"));
    }

    public void test() {
        long l1 = System.nanoTime();
        List<String> list = findPrice("huawei");
        long l2 = System.nanoTime();
        log.info("done = {}", (l2 - l1));
        log.info("test list {}", list);
    }

    public void test1() {
        long l1 = System.nanoTime();
        List<String> list = findPrice1("huawei");
        long l2 = System.nanoTime();
        log.info("parallel done = {}", (l2 - l1));
        log.info("test1 list {}", list);
    }

    public void test2() {
        long l1 = System.nanoTime();
        List<CompletableFuture<String>> list = findPrice2("huawei");
        long l2 = System.nanoTime();
        log.info("completablefuture done = {}", (l2 - l1));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        try {
            list.stream().forEach(obj -> {
                try {
                    System.out.println("obj: " + obj.get(10, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test3() {
        long l1 = System.nanoTime();
        List<String> list = findPrice3("huawei");
        long l2 = System.nanoTime();
        log.info("completablefuture done = {}", (l2 - l1));
        log.info("test3 list {}", list);
    }

    public void test4(Executor executor) {
        long l1 = System.nanoTime();
        List<String> list = findPrice4("huawei", executor);
        long l2 = System.nanoTime();
        log.info("completablefuture done = {}", (l2 - l1));
        log.info("test4 list {}", list);
    }

    public void testNew() {
        long l1 = System.nanoTime();
        List<String> list = findPriceNew("huawei");
        long l2 = System.nanoTime();
        log.info("completablefuture done = {}", (l2 - l1));
        log.info("test5 list {}", list);
    }

    public void testNew1(Executor executor) {
        long l1 = System.nanoTime();
        List<String> list = findPrice4("huawei", executor);
        long l2 = System.nanoTime();
        log.info("completablefuture done = {}", (l2 - l1));
        log.info("testNew1 list {}", list);
    }

    public void testNew2(Executor executor) {
        long l1 = System.nanoTime();
        CompletableFuture[] huawei = findPriceNew2("huawei", executor).map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(huawei).join();
        long l2 = System.nanoTime();
        log.info("completablefuture done = {}", (l2 - l1));
        log.info("testNew1 huawei {}", huawei);
    }

    public void testCombine() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> "2"),
                        (i, s) -> i + s
                );
        String join = stringCompletableFuture.join();
        log.info("join {}", join);
    }

    public void testException() {
        CompletableFuture<String> f0 = CompletableFuture.supplyAsync(() -> String.valueOf(7 / 2))
                .thenApply(r -> String.valueOf(Integer.parseInt(r) * 10))
                .exceptionally(e -> {
                    e.printStackTrace();
                    return e.getMessage();
                });
        log.info("v {}", f0.join());
    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.initData();
//        shop.test();
//        shop.test1();
//        shop.test3();
/*        try {
            Executor executor = Executors.newFixedThreadPool(Math.min(shop.shops.size(), 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    //使用守护线程, 这种方式不会阻止程序的关停
                    thread.setDaemon(true);
                    return thread;
                }
            });
//            shop.test4(executor);
            shop.testNew2(executor);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

//        shop.testNew();

//        shop.testCombine();
        shop.testException();
    }
}
