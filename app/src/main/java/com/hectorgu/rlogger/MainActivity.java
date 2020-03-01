package com.hectorgu.rlogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hectorgu.logger.RLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //自定义全局TAG
//        RLog.setCustomTag("bilibili");
        //设置是否可被输出
//        RLog.isLoggable(Log.DEBUG, "bilibili");
        //打印json
        RLog.json(Log.DEBUG, "{ \"name\": \"John\", \"age\":18}", 5);
        RLog.logger(Log.DEBUG, "hello=%s", "world");
    }

}
