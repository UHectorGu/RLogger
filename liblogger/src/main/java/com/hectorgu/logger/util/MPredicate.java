package com.hectorgu.logger.util;

public interface MPredicate<K, V> {
    boolean accept(K key, V value);
}
