package com.hectorgu.logger.strategy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hectorgu.logger.annotation.LogType;

public interface IStrategy {

    @LogType
    int getLogcatType();

    void log(int priority, @NonNull String tag, @NonNull String format, @Nullable Object... args);
}
