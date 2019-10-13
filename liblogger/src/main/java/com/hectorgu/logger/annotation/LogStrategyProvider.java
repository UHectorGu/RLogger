package com.hectorgu.logger.annotation;

import android.util.Log;

import com.hectorgu.logger.RLog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogStrategyProvider {
    @LogType int type() default RLog.LOGCAT_TYPE_ORIGINAL;

}
