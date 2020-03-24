package com.github.maxwell.base.classload;

public class ClassTest {
    private static ClassTest ClassTest = new ClassTest();
    public static int count1;
    public static int count2 = 0;

    private ClassTest() {
        count1++;
        count2++;
    }

    public static ClassTest getInstance() {
        return ClassTest;
    }

}
