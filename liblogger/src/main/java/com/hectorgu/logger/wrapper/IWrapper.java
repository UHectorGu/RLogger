package com.hectorgu.logger.wrapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hectorgu.logger.annotation.LogType;

public interface IWrapper {

    @LogType
    int getLogcatType();

    boolean isLoggable(int priority, @Nullable String tag);

    void log(int priority, @NonNull String tag, @NonNull String format, @Nullable Object... args);
}
