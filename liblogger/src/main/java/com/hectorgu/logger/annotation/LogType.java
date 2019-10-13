package com.hectorgu.logger.annotation;

import android.support.annotation.IntDef;

import com.hectorgu.logger.RLog;

import java.lang.annotation.Retention;

import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;


@Target({FIELD, METHOD, PARAMETER})
@Retention(SOURCE)
@IntDef({RLog.LOGCAT_TYPE_ORIGINAL, RLog.LOGCAT_TYPE_JSON, RLog.LOGCAT_TYPE_XML})
public @interface LogType {
}
