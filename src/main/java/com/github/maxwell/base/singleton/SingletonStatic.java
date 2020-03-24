package com.github.maxwell.base.singleton;

/**
 * 静态内部类实现单例
 */
public class SingletonStatic {

    SingletonStatic() {
    }

    private static class InstanceHolder {
        public static SingletonStatic singleton = new SingletonStatic();
    }

    public static SingletonStatic getInstance() {
        //调用内部属性
        return InstanceHolder.singleton;
    }
}
