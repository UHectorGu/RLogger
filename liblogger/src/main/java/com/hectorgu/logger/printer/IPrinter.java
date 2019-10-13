package com.hectorgu.logger.printer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hectorgu.logger.annotation.LogType;
import com.hectorgu.logger.strategy.IStrategy;

public interface IPrinter {

    void setTag(String tag);

    String getTag();

    void addStrategy(IStrategy strategy);

    void isLoggable(int priority, @Nullable String tag);

    void v(@NonNull String format, @Nullable Object... args);

    void d(@NonNull String format, @Nullable Object... args);

    void i(@NonNull String format, @Nullable Object... args);

    void w(@NonNull String format, @Nullable Object... args);

    void e(@NonNull String format, @Nullable Object... args);

    void json(int priority, @NonNull String format, @Nullable String json);

    void json(int priority, @NonNull String format, @Nullable String json, int indentSpaces);

    void xml(int priority, @NonNull String format, @Nullable String xml);

    void xml(int priority, @NonNull String format, @Nullable String xml, int indentSpaces);

    void log(int priority, @LogType int type, @NonNull String format, @Nullable Object... args);

    void recycle();
}
