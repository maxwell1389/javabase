package com.github.maxwell.base.thread.future;

public class Discount {
    public enum Code {
        //无     银         金         铂金          钻石
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        //百分比
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        //将商品的原始价格和折扣力度传入,返回一个新价格
        return quote.getShopName() + " price = " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static String apply(double price, Code discountCode) {
        delay();  //模拟服务响应的延迟
        //新价格
        return (price * (100 - discountCode.percentage) / 100) + "";
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
