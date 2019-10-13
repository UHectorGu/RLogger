package com.hectorgu.logger.strategy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hectorgu.logger.RLog;
import com.hectorgu.logger.entity.Xml;
import com.hectorgu.logger.util.LibCompat;
import com.hectorgu.logger.util.MessageFormatter;

import javax.xml.transform.TransformerException;

public class XmlStrategy implements IStrategy {

    @Override
    public int getLogcatType() {
        return RLog.LOGCAT_TYPE_XML;
    }

    @Override
    public void log(int priority, @NonNull String tag, @NonNull String format, @Nullable Object... args) {
        try {

            LibCompat.requireNonNull(args, "参数缺失");
            Xml entity = new Xml(args);
            String xml = LibCompat.requireNonNull(entity.getXml(), "Empty/Null xml content");
            int indentSpaces = entity.optIndentSpaces(0);
            String afterXml = MessageFormatter.newInstance().formatXml(format, xml, indentSpaces);
            Log.println(priority, tag, afterXml);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
