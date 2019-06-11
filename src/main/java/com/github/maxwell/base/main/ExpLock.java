package com.github.maxwell.base.main;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpLock {
    private String key;
    private String name;

    public boolean lock(boolean wait) {
        if (wait)
            throw new LockException(this.key + " get lock " + this.name + " timeout");

        return true;
    }

    public boolean unlock() {
        log.info("{} release {} success", this.key, this.name);


        return true;
    }

    public ExpLock(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
