# RLogger

[![Download][jcenter-svg]][jcenter-link]

自封装自用的简单易Logger工具，后期希望封装更多功能

## 使用

#### gradle

在build.gradle文件添加以下语句

```gradle

implementation 'com.hectorgu:liblogger:1.0.x'

```

#### 基础

格式化输出, 内部对输出对象做了处理，可以直接输出对象

```java

User user = new User();
RLog.d("User=%s", user);
RLog.v("User=%s", user);
...
```

#### json

json(int priority, String json);

json(int priority, String json, int indentSpaces);

```java

String json = "{ \"name\": \"John\", \"age\":18}";
RLog.json(RLog.DEBUG, json);
或
RLog.json(RLog.DEBUG, json, 4);

```

#### Tag

可自定义tag

```java

RLog.setCustomTag("Service");

```

#### isLoggable

只有Priority和Tag都能对应上才能屏蔽Log

```java

RLog.isLoggable(Log.DEBUG, "Service");

```

## 后期添加功能

当前版本设计得不好，还有很多问题，也是提供给自己使用，后期我会以表格形式输出数据，重新设计得更易用一点，让看Log更清晰

[jcenter-svg]: https://api.bintray.com/packages/hectorgu/maven/liblogger/images/download.svg?version=1.0.3
[jcenter-link]: https://bintray.com/hectorgu/maven/liblogger/1.0.3/link
