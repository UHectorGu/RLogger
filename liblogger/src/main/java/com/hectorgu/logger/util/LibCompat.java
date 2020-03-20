package com.hectorgu.logger.util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings("ALL")
public final class LibCompat {

    public static <T> Collection<T> filter(@NonNull T[] target, @NonNull Predicate<T> predicate) {
        return filter(Arrays.asList(target), predicate);
    }

    public static <T> T select(@NonNull T[] target, @NonNull Predicate<T> predicate) {
        return select(target, predicate, null);
    }

    public static <T> T select(@NonNull T[] target, @NonNull Predicate<T> predicate, T defaultValue) {
        return select(Arrays.asList(target), predicate, defaultValue);
    }

    public static <T> T select(@NonNull Collection<T> target, @NonNull Predicate<T> predicate) {
        return select(target, predicate, null);
    }

    public static <T> void forEach(@NonNull T[] target, @NonNull Consumer<T> consumer) {
        forEach(Arrays.asList(target), consumer);
    }

    public static <K, V> Collection<V> filter(@NonNull Map<K, V> target, @NonNull MPredicate<K, V> predicate) {
        Collection<V> result = new ArrayList<V>();
        for (K key : target.keySet()) {
            V value = target.get(key);
            if (predicate.accept(key, value)) {
                result.add(value);
            }
        }
        return result;
    }

    public static <T> Collection<T> filter(@NonNull Collection<T> target, @NonNull Predicate<T> predicate) {
        Collection<T> result = new ArrayList<T>();
        for (T element : target) {
            if (predicate.accept(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> T select(@NonNull Collection<T> target, @NonNull Predicate<T> predicate, T defaultValue) {
        T result = defaultValue;
        for (T element : target) {
            if (!predicate.accept(element))
                continue;
            result = element;
            break;
        }
        return result;
    }

    public static <K, V> V select(@NonNull Map<K, V> target, @NonNull MPredicate<K, V> predicate) {
        V result = null;
        for (K key : target.keySet()) {
            V value = target.get(key);
            if (!predicate.accept(key, value))
                continue;
            result = value;
            break;
        }
        return result;
    }

    public static <T> void forEach(@NonNull Collection<T> target, @NonNull Consumer<T> consumer) {
        for (T t : target) {
            consumer.accept(t);
        }
    }

    public static <K, V> void forEach(@NonNull Map<K, V> target, @NonNull MConsumer<K, V> consumer) {
        for (K key : target.keySet()) {
            V value = target.get(key);
            consumer.accept(key, value);
        }
    }

    public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }

}
