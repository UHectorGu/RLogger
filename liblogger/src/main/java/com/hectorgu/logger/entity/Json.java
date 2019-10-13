package com.hectorgu.logger.entity;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Json implements Serializable {

    private String json;
    private int indentSpaces;

    public Json(Object... args) {
        json = String.valueOf(args[0]);
        indentSpaces = Integer.valueOf(String.valueOf(args[1]));
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int optIndentSpaces(int def) {
        return indentSpaces > 0 ? indentSpaces : def;
    }

    public void setIndentSpaces(int indentSpaces) {
        this.indentSpaces = indentSpaces;
    }

    @Override
    @NonNull
    public String toString() {
        return "JsonEntity{" +
                "json='" + json + '\'' +
                ", indentSpaces=" + indentSpaces +
                '}';
    }
}
