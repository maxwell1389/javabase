package com.github.maxwell.base.main;

public class MathRandom {
    /**
     * 0出现的概率为%50
     */
    public static double rate0 = 0.50;
    /**
     * 1出现的概率为%20
     */
    public static double rate1 = 0.25;
    /**
     * 2出现的概率为%15
     */
    public static double rate2 = 0.25;
/*    *//**
     * 3出现的概率为%10
     *//*
    public static double rate3 = 0.10;
    *//**
     * 4出现的概率为%4
     *//*
    public static double rate4 = 0.04;
    *//**
     * 5出现的概率为%1
     *//*
    public static double rate5 = 0.01;*/

    /**
     * Math.random()产生一个double型的随机数，判断一下
     * 例如0出现的概率为%50，则介于0到0.50中间的返回0
     *
     * @return int
     */
    private double PercentageRandom() {
        double randomNumber;
        randomNumber = Math.random();
        System.out.println("r:" +
                "" + randomNumber);
        if (randomNumber >= 0 && randomNumber <= rate0) {
            return 1.5;
        } else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
            return 2.0;
        } else if (randomNumber >= rate0 + rate1
                && randomNumber <= rate0 + rate1 + rate2) {
            return 3.0;
        }
/*        else if (randomNumber >= rate0 + rate1 + rate2
                && randomNumber <= rate0 + rate1 + rate2 + rate3) {
            return 3;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3
                && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
            return 4;
        } else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4
                && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4
                + rate5) {
            return 5;
        }*/
        return -1.0;
    }

    /**
     * 测试主程序
     *
     * @param agrs
     */
    public static void main(String[] agrs) {
        int i = 0;
        MathRandom a = new MathRandom();
        int i15 = 0;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < 100; i++)//打印100个测试概率的准确性
        {
            double tmp = a.PercentageRandom();
            if (tmp == 1.5) {
                i15 = i15 + 1;
            }
            if (tmp == 2.0) {
                i2 = i2 + 1;
            }
            if (tmp == 3.0) {
                i3 = i3 + 1;
            }
        }
        System.out.println("1.5: " + i15 + ", 2.0:" + i2 + ", 3.0:" + i3);
    }
}