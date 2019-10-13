package com.hectorgu.logger.strategy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hectorgu.logger.RLog;
import com.hectorgu.logger.entity.Json;
import com.hectorgu.logger.util.LibCompat;
import com.hectorgu.logger.util.MessageFormatter;

import org.json.JSONException;

public class JsonStrategy implements IStrategy {
    @Override
    public int getLogcatType() {
        return RLog.LOGCAT_TYPE_JSON;
    }

    @Override
    public void log(int priority, @NonNull String tag, @NonNull String format, @Nullable Object... args) {
        try {

            LibCompat.requireNonNull(args, "参数缺失");
            Json entity = new Json(args);

            String json = LibCompat.requireNonNull(entity.getJson(), "Empty/Null json content");
            int indentSpaces = entity.optIndentSpaces(0);

            String afterJson = MessageFormatter.newInstance().formatJson(format, json, indentSpaces);
            Log.println(priority, tag, afterJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
