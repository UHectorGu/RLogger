package com.hectorgu.logger.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("ALL")
public final class LibCompat {

    public static <T> Collection<T> filter(T[] target, Predicate<T> predicate) {
        return filter(Arrays.asList(target), predicate);
    }

    public static <T> Collection<T> filter(Collection<T> target, Predicate<T> predicate) {
        Collection<T> result = new ArrayList<T>();
        for (T element : target) {
            if (predicate.accept(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> T select(T[] target, Predicate<T> predicate) {
        return select(target, predicate, null);
    }

    public static <T> T select(T[] target, Predicate<T> predicate, T defaultValue) {
        return select(Arrays.asList(target), predicate, defaultValue);
    }

    public static <T> T select(Collection<T> target, Predicate<T> predicate) {
        return select(target, predicate, null);
    }

    public static <T> T select(Collection<T> target, Predicate<T> predicate, T defaultValue) {
        T result = defaultValue;
        for (T element : target) {
            if (!predicate.accept(element))
                continue;
            result = element;
            break;
        }
        return result;
    }

    public static <T> void forEach(Collection<T> target, Consumer<T> consumer) {
        requireNonNull(consumer);
        for (T t : target) {
            consumer.accept(t);
        }
    }

    public static <T> void forEach(T[] target, Consumer<T> consumer) {
        forEach(Arrays.asList(target), consumer);
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
