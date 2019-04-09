package com.github.maxwell.base.lru;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 最近最少使用缓存
 */
@Slf4j
public class LRUCache2<K, V> {
    private final int cacheSize;
    private LinkedList<K> cacheList = new LinkedList<K>();
    private Map<K, V> mapCache = new HashMap<K, V>();

    public LRUCache2(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    /**
     *
     * @param key
     * @param value
     */
    public synchronized void put(K key, V value){
        if(!mapCache.containsKey(key)){
            //是否超出容量
            if(mapCache.size() >= cacheSize){
                removeLastElement();
            }
            cacheList.addFirst(key);
            mapCache.put(key, value);
        }else {
            moveToFirst(key);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public V get(K key) {
        if (!mapCache.containsKey(key)) {
            return null;
        }
        moveToFirst(key);
        return mapCache.get(key);
    }

    /**
     *
     * @param key
     */
    private synchronized void moveToFirst(K key) {
        cacheList.remove(key);
        cacheList.addFirst(key);
    }

    /**
     *
     */
    private synchronized void removeLastElement(){
        K key = cacheList.removeLast();
        mapCache.remove(key);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return cacheList.toString();
    }


}


