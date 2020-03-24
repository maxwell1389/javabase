package com.github.maxwell.base.main;

public class Demo {
    public static void main(String[] args) {

        boolean flag = 1/0 == 0 && 10%2 == 1 && 10/3 ==0;
        System.out.println(flag ? "mldn" : "yootk");
/*
        int numm = 0;
        int xx = 10;
        while (xx > 0) {
            numm += xx;
        }
        System.out.println(numm);*/



        int num = 68;
        char c = (char) num;
        System.out.println(c);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
            if (i % 3 == 0) {
                break;
            }
        }
        System.out.println(sum);

        int x = 10;
        double y = 20.2;
        long z = 10l;
        float f = 1.3f;
        String str = "" + x + y * z;
        System.out.println(str);

        int num1 = Integer.MAX_VALUE;
        num1 += 2l;
        System.out.println(num1);

        String str1 = "";
        for (int i = 0; i < 5; i++) {
            str1 += i;
        }
        System.out.println(str1);

        int num2 = Integer.MAX_VALUE;
        long temp = num2 + 2l;
        System.out.println(num2);

        int num3 = 50;
        num3 = num3 ++ * 2;
        System.out.println(num3);

    }

    int main() {
        return 0;
    }

    public class A {
    }

}
