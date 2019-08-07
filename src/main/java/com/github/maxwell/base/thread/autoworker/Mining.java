package com.github.maxwell.base.thread.autoworker;

/**
 * 矿类
 */
public class Mining {
    private String key;
    private double amount; //每秒挖矿数量

    public Boolean execute() {
        System.out.println(Thread.currentThread().getName() + " executed " + this + " mining: " + amount);
        return false;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
