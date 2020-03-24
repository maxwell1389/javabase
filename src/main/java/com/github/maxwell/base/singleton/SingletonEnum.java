package com.github.maxwell.base.singleton;

/**
 * 枚举类实现单例
 */
public class SingletonEnum {

    private SingletonEnum() {
    }

    /**
     * 枚举类是线程安全并且只装载一次
     */
    private enum Singleton {
        INSTANCE;
        private final SingletonEnum instance;

        Singleton() {
            instance = new SingletonEnum();
        }

        private SingletonEnum getInstance() {
            return instance;
        }
    }

    public static SingletonEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
