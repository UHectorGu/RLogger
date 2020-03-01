# RLogger

[![Download][jcenter-svg]][jcenter-link]

自封装自用的简单易Logger工具.

## 下载

Gradle:

```gradle
implementation 'com.hectorgu:liblogger:1.0.5'
```

Maven:

```xml
<dependency>
	<groupId>com.hectorgu</groupId>
	<artifactId>liblogger</artifactId>
	<version>1.0.5</version>
	<type>pom</type>
</dependency>
```

## 使用

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

[jcenter-svg]: https://api.bintray.com/packages/hectorgu/maven/liblogger/images/download.svg?version=1.0.5
[jcenter-link]: https://bintray.com/hectorgu/maven/liblogger/1.0.5/link