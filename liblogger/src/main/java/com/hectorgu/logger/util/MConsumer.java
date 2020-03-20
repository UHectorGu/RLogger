package com.hectorgu.logger.util;

public interface MConsumer<K, V> {
    void accept(K key, V value);
}
