package com.github.maxwell.base.main;

public class Happy {
    public static void main(String[] args) {
        int i = 1;
        int j = i++;
        if ((i == (++j)) && ((i++) == j)) {
            i += j;
        }
        System.out.printf("i = " + i);
    }
}
