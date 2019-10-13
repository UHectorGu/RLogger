package com.hectorgu.logger.wrapper;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.hectorgu.logger.annotation.LogStrategyProvider;
import com.hectorgu.logger.printer.IPrinter;
import com.hectorgu.logger.strategy.IStrategy;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public abstract class AbstractWrapper implements IWrapper {

    private static final String NAME = AbstractWrapper.class.getSimpleName();

    private IPrinter mPrinter;
    private IStrategy mStrategy;

    AbstractWrapper(IStrategy strategy, IPrinter printer) {
        mPrinter = printer;
        mStrategy = strategy;
    }

    @Override
    public int getLogcatType() {
        return mStrategy.getLogcatType();
    }

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
        return TextUtils.isEmpty(tag) || !mPrinter.getTag().contains(tag);
    }

    IStrategy getStrategy() {
        return mStrategy;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null) throw new RuntimeException("Is a null object");
        if (!(obj instanceof IWrapper))
            throw new RuntimeException("it not a IWrapper object");
        IWrapper wrapper = (IWrapper) obj;
        return getLogcatType() == wrapper.getLogcatType();
    }

    @Override
    public int hashCode() {
        return NAME.hashCode() * 5 + getLogcatType();
    }
}
