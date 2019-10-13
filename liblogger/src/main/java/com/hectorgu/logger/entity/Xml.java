package com.hectorgu.logger.entity;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Xml implements Serializable {

    private String xml;
    private int indentSpaces;

    public Xml(Object... args) {
        xml = String.valueOf(args[0]);
        indentSpaces = Integer.valueOf(String.valueOf(args[1]));
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
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
        return "XmlEntity{" +
                "xml='" + xml + '\'' +
                ", indentSpaces=" + indentSpaces +
                '}';
    }
}
