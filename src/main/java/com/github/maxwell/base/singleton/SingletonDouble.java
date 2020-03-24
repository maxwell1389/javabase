package com.github.maxwell.base.singleton;

/**
 * 双重检查锁实现单例
 */
public class SingletonDouble {
    private static volatile SingletonDouble instance;

    private SingletonDouble() {
    }

    public static SingletonDouble getInstance() {
        if (null == instance)
            synchronized (SingletonDouble.class) {
                if (null == instance)
                    instance = new SingletonDouble();
            }
        return instance;
    }
}
