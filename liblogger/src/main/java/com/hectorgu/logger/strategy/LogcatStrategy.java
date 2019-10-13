package com.hectorgu.logger.strategy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hectorgu.logger.RLog;
import com.hectorgu.logger.annotation.LogStrategyProvider;
import com.hectorgu.logger.util.MessageFormatter;

@LogStrategyProvider
public class LogcatStrategy implements IStrategy {

    @Override
    public int getLogcatType() {
        return RLog.LOGCAT_TYPE_ORIGINAL;
    }

    @Override
    public void log(int priority, @NonNull String tag, @NonNull String format, @Nullable Object... args) {
        Log.println(priority, tag, MessageFormatter.newInstance().format(format, args));
    }

}
