# awesome-java-log-aop

## 实现方案

先定义注解，然后使用切面处理

## 实现效果

``` text
2024-07-10 23:43:08.070  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : ================== start ==================
2024-07-10 23:43:08.070  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : URL              : http://localhost:8083/log-aop/test/get
2024-07-10 23:43:08.070  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : HTTP Method      : GET
2024-07-10 23:43:08.071  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : Class Method     : com.yilan.awesome.rest.TestController.get
2024-07-10 23:43:08.071  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : IP               : 0:0:0:0:0:0:0:1
2024-07-10 23:43:08.071  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : Request Args     : 111
2024-07-10 23:43:08.073  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : Response Args    : 测试AOP打印日志功能, id:111
2024-07-10 23:43:08.073  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : Time-Consuming   : 2 ms
2024-07-10 23:43:08.074  INFO 22522 --- [nio-8083-exec-2] com.yilan.awesome.aspect.LogAspect       : ================== end ===================
```

## 扩展

可以在切面中添加保存日志的操作：
* 在环绕通知中保存正常日志
* 在AfterThrowing中保存异常日志

具体可参考eladmin的logging模块