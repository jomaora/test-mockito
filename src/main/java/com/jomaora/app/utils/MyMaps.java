package com.jomaora.app.utils;

import java.util.*;

public class MyMaps<K, V> {

    private int size;
    private List<K> myKeys;
    private List<V> myValues;

    private MyMaps(Set<K> keys, Collection<V> values) {
        if ( keys.size() == values.size() ) {
            size = keys.size();
            myKeys = new ArrayList<K>(keys);
            myValues = new ArrayList<V>(values);
        }
    }

    public static <K, V> MyMaps<K, V> create(Map<K, V> map) {
        return new MyMaps(map.keySet(), map.values());
    }

    public K getKey(V value) {
        if ( myValues.contains(value) ) {
            return  myKeys.get( myValues.indexOf(value) );
        }
        return null;
    }

    public V getValue(K key) {
        if ( myKeys.contains(key) ) {
            return  myValues.get( myKeys.indexOf(key) );
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}