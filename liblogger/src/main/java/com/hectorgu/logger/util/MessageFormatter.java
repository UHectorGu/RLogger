package com.hectorgu.logger.util;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public final class MessageFormatter {

    private volatile static MessageFormatter INSTANCE;

    private static final String NEW_LINE = " \n";

    private MessageFormatter() {
    }

    public static MessageFormatter newInstance() {
        if (INSTANCE == null) {
            synchronized (MessageFormatter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MessageFormatter();
                }
            }
        }
        return INSTANCE;
    }

    private static Object[] parseArgs(Object... args) {
        final List<Object> linkedArgs = new LinkedList<>();
        LibCompat.forEach(args, a -> {
            if (a instanceof Integer
                    || a instanceof Double
                    || a instanceof Boolean
                    || a instanceof Character
                    || a instanceof Float
                    || a instanceof BigDecimal
                    || a instanceof BigInteger) {
                linkedArgs.add(a);
            } else {
                linkedArgs.add(String.valueOf(a));
            }
        });
        return linkedArgs.toArray();
    }

    public String format(@NonNull String format, Object... args) {
        Object[] newArgs = parseArgs(args);
        return (newArgs != null && newArgs.length > 0) ? String.format(format, newArgs) : format;
    }

    public String formatJson(@NonNull String format, String json, int indentSpaces) throws JSONException {

        String msg = "";
        JSONExtendsTokener tokener = new JSONExtendsTokener(json);

        if (tokener.isObject()) {

            JSONObject object = LibCompat.requireNonNull(tokener.obtJSONObject());
            msg = indentSpaces <= 0 ? object.toString() : object.toString(indentSpaces);
            return format(format, NEW_LINE + msg);

        } else if (tokener.isArray()) {

            JSONArray array = LibCompat.requireNonNull(tokener.obtJSONArray());
            msg = indentSpaces <= 0 ? LibCompat.requireNonNull(tokener.obtJSONArray()).toString() : array.toString(indentSpaces);
            return format(format, NEW_LINE + msg);

        }
        return "";
    }

    public String formatXml(String format, String xml, int indentSpaces) throws TransformerException {

        StreamSource xmlInput = new StreamSource(new StringReader(xml));
        StreamResult xmlOutput = new StreamResult(new StringWriter());
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, indentSpaces <= 0 ? "no" : "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indentSpaces));
        transformer.transform(xmlInput, xmlOutput);
        Writer writer = xmlOutput.getWriter();
        return format(format, writer.toString().replaceFirst(">", ">\n"));

    }
}
