package com.hectorgu.logger.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

@SuppressWarnings("ALL")
public class JSONExtendsTokener extends JSONTokener {

    private Object mNextValue = null;
    /**
     * @param in JSON encoded string. Null is not permitted and will yield a
     *           tokener that throws {@code NullPointerExceptions} when methods are
     *           called.
     */
    public JSONExtendsTokener(String in) {
        super(in);
        try {
            mNextValue = nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isObject() throws JSONException {
        return mNextValue instanceof JSONObject;
    }

    public boolean isArray() throws JSONException {
        return mNextValue instanceof JSONArray;
    }

    public JSONObject obtJSONObject() throws JSONException {
        return (JSONObject) mNextValue;
    }

    public JSONArray obtJSONArray() throws JSONException {
        return (JSONArray) mNextValue;
    }
}
