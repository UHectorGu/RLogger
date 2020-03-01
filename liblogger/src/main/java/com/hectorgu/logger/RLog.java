package com.hectorgu.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hectorgu.logger.annotation.LogType;
import com.hectorgu.logger.printer.IPrinter;
import com.hectorgu.logger.printer.LogcatPrinter;
import com.hectorgu.logger.strategy.JsonStrategy;
import com.hectorgu.logger.strategy.LogcatStrategy;
import com.hectorgu.logger.strategy.XmlStrategy;

@SuppressWarnings({"unused", "SameParameterValue"})
public final class RLog {

    public static final int LOGCAT_TYPE_ORIGINAL = 0;
    public static final int LOGCAT_TYPE_JSON = 1;
    public static final int LOGCAT_TYPE_XML = 2;

    private static IPrinter sPrinter = new LogcatPrinter();

    static {
        sPrinter.addStrategy(new LogcatStrategy());
        sPrinter.addStrategy(new JsonStrategy());
        sPrinter.addStrategy(new XmlStrategy());
    }

    public static void logger(int priority, @NonNull String format, @Nullable Object... args) {
        sPrinter.log(priority, RLog.LOGCAT_TYPE_ORIGINAL, format, args);
    }

    public static void d(String format, Object... msg) {
        sPrinter.d(format, msg);
    }

    public static void i(String format, Object... msg) {
        sPrinter.i(format, msg);
    }

    public static void e(String format, Object... msg) {
        sPrinter.e(format, msg);
    }

    public static void v(String format, Object... msg) {
        sPrinter.v(format, msg);
    }

    public static void w(String format, Object... msg) {
        sPrinter.w(format, msg);
    }

    public static void isLoggable(int priority, String tag) {
        sPrinter.isLoggable(priority, tag);
    }

    public static void setCustomTag(String tag) {
        sPrinter.setTag(tag);
    }

    public static void json(int priority, String json) {
        json(priority, json, 2);
    }

    public static void json(int priority, String json, int indentSpaces) {
        sPrinter.json(priority, "%s", json, indentSpaces);
    }

    public static void xml(int priority, String xml) {
        xml(priority, xml, 2);
    }

    public static void xml(int priority, String xml, int indentSpaces) {
        sPrinter.xml(priority, "%s", xml, indentSpaces);
    }

    public void recycle() {
        sPrinter.recycle();
        sPrinter = null;
    }
}
