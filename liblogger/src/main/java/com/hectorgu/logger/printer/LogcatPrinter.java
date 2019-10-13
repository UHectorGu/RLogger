package com.hectorgu.logger.printer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.hectorgu.logger.RLog;
import com.hectorgu.logger.annotation.LogType;
import com.hectorgu.logger.strategy.IStrategy;
import com.hectorgu.logger.util.LibCompat;
import com.hectorgu.logger.util.MessageFormatter;
import com.hectorgu.logger.util.RealStackTraceElement;
import com.hectorgu.logger.wrapper.IWrapper;
import com.hectorgu.logger.wrapper.LogcatWrapper;

import java.util.ArrayList;
import java.util.List;

public final class LogcatPrinter implements IPrinter {

    private static final RealStackTraceElement STACKTRACE = new RealStackTraceElement(RLog.class);

    private String mTag = "";

    private SparseArray<String> DISABLE_TAGS = new SparseArray<>();
    private List<IWrapper> mWrappers = new ArrayList<>();

    @Override
    public void setTag(String tag) {
        mTag = tag;
    }

    @Override
    public String getTag() {
        return mTag;
    }

    @Override
    public void addStrategy(IStrategy strategy) {
        mWrappers.add(new LogcatWrapper(strategy, this));
    }

    @Override
    public void isLoggable(int priority, @Nullable String tag) {
        DISABLE_TAGS.append(priority, tag);
    }

    @Override
    public void v(@NonNull String format, @Nullable Object... args) {
        log(Log.VERBOSE, RLog.LOGCAT_TYPE_ORIGINAL, format, args);
    }

    @Override
    public void d(@NonNull String format, @Nullable Object... args) {
        log(Log.DEBUG, RLog.LOGCAT_TYPE_ORIGINAL, format, args);
    }

    @Override
    public void i(@NonNull String format, @Nullable Object... args) {
        log(Log.INFO, RLog.LOGCAT_TYPE_ORIGINAL, format, args);
    }

    @Override
    public void w(@NonNull String format, @Nullable Object... args) {
        log(Log.WARN, RLog.LOGCAT_TYPE_ORIGINAL, format, args);
    }

    @Override
    public void e(@NonNull String format, @Nullable Object... args) {
        log(Log.ERROR, RLog.LOGCAT_TYPE_ORIGINAL, format, args);
    }

    @Override
    public void json(int priority, @NonNull String format, @Nullable String json) {
        json(priority, format, json, 2);
    }

    @Override
    public void json(int priority, @NonNull String format, @Nullable String json, int indentSpaces) {
        log(priority, RLog.LOGCAT_TYPE_JSON, format, json, indentSpaces);
    }

    @Override
    public void xml(int priority, @NonNull String format, @Nullable String xml) {
        xml(priority, format, xml, 2);
    }

    @Override
    public void xml(int priority, @NonNull String format, @Nullable String xml, int indentSpaces) {
        log(priority, RLog.LOGCAT_TYPE_XML, format, xml, indentSpaces);
    }

    @Override
    public void log(int priority, @LogType int type, @NonNull String format, @Nullable Object... args) {
        IWrapper wrapper = LibCompat.select(mWrappers, a -> a.getLogcatType() == type);
        if (wrapper != null) {
            STACKTRACE.notifyChange();

            MessageFormatter formatter = MessageFormatter.newInstance();
            StackTraceElement stackTraceElement = STACKTRACE.get();
            String tag = formatter.format("[%s-%s]",
                    RLog.class.getSimpleName(),
                    TextUtils.isEmpty(mTag)
                            ? formatter.format("%s(%s:%s)",
                            stackTraceElement.getMethodName(),
                            stackTraceElement.getFileName(),
                            stackTraceElement.getLineNumber())
                            : mTag);

            if (wrapper.isLoggable(priority, DISABLE_TAGS.get(priority)))
                wrapper.log(priority, tag, format, args);
        }

    }

    @Override
    public void recycle() {
        mWrappers.clear();
        mWrappers = null;
    }


}
