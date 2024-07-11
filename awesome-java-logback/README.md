# awesome-java-logback

> 此 demo 主要演示了如何使用 logback 记录程序运行过程中的日志，
> 以及如何配置 logback，可以同时生成控制台日志和文件日志记录，
> 文件日志以日期和大小进行拆分生成。

## 参考

https://blog.csdn.net/feiying0canglang/article/details/120446300

## logback.xml和logback-spring.xml的区别

logback和logback-spring.xml都可以用来配置logback，但是2者的加载顺序是不一样的。

logback.xml—>application.properties—>logback-spring.xml.

logback.xml加载早于application.properties，所以如果你在logback.xml使用了变量时，而恰好这个变量是写在application.properties时，
那么就会获取不到，只要改成logback-spring.xml就可以解决。

## logback配置文件详解

https://juejin.cn/post/7200549600590282789