package com.hectorgu.logger.util;

import java.util.concurrent.atomic.AtomicBoolean;

public final class RealStackTraceElement {

    private static final ThreadLocal<StackTraceElement> realStackTraceElement = new ThreadLocal<>();

    private Class<?> mTarget;

    public RealStackTraceElement(Class<?> target) {
        mTarget = target;
        query();
    }

    private void set(StackTraceElement e) {
        realStackTraceElement.set(e);
    }

    public StackTraceElement get() {
        return realStackTraceElement.get();
    }

    public void notifyChange() {
        query();
    }

    private static void checkTarget(Class<?> target) {
        if(target == null)
            throw new NullPointerException("Search Target Class is " + target);
    }

    private void query() {
        checkTarget(mTarget);
        AtomicBoolean isStackTrace = new AtomicBoolean(false);
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        set(LibCompat.select(elements, e -> {
            boolean isTarget = e.getClassName().equals(mTarget.getName());
            if (isTarget)
                isStackTrace.set(true);
            return isStackTrace.get() && !isTarget;
        }));
    }
}
