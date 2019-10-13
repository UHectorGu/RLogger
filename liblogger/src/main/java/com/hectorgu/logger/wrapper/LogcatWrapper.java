package com.hectorgu.logger.wrapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hectorgu.logger.printer.IPrinter;
import com.hectorgu.logger.strategy.IStrategy;

@SuppressWarnings("unused")
public class LogcatWrapper extends AbstractWrapper {


    public LogcatWrapper(IStrategy strategy, IPrinter printer) {
        super(strategy, printer);
    }

    @Override
    public void log(int priority, @NonNull String tag, @NonNull String format, @Nullable Object... args) {
        getStrategy().log(priority, tag, format, args);
    }

}