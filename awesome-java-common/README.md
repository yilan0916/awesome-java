# awesome-java-common

# 学习教程

Spring Boot 发送邮件（Thymeleaf 构建邮件模版）
<https://blog.csdn.net/Hack_Different/article/details/115544413>
Thymeleaf基本使用
<https://blog.csdn.net/Lzy410992/article/details/115371017>
一看就会的Spring boot 发送邮件 + 使用html模板发送邮件
<https://blog.csdn.net/qq1328585964/article/details/123737477>

依赖

```xml
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-mail</artifactId>  
</dependency>  
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-thymeleaf</artifactId>  
</dependency>
```

application.yml

```yml
spring:  
  profiles:  
    active: dev  
  application:  
    name: set  
  jackson:  
    time-zone: GMT+8  
  mail:  
    host: hikml36.hikvision.com.cn  
    port: 25  
    username: VtestMail@hikvision.com.cn  
    password: S4B%d}uwQ  
  thymeleaf:  
	  prefix: classpath:/templates/  
	  cache: false # 关闭Thymeleaf的缓存
  servlet:  
    multipart:  
      max-file-size: 1GB  
      max-request-size: 1GB  
      enabled: true  
  
server:  
  tomcat:  
    accept-count: 2048  
    max-threads: 1024  
    max-connections: 20000  
    max-http-form-post-size: 1024MB  
management:  
  endpoints:  
    web:  
      exposure:  
        include: "*"  
  endpoint:  
    health:  
      show-details: ALWAYS
```

application-dev.yml

```yml
server:  
  port: 8090  
  servlet:  
    context-path: /set  
  
shiro:  
  cas: https://ssouat.hikvision.com  
  server: http://10.18.138.46:8090/set  
  
management:  
  endpoints:  
    web:  
      exposure:  
        include: "*"  
  
  
spring:  
  datasource:  
    hikari:  
      minimum-idle: 36  
    dynamic:  
      primary: master # 设置默认的数据源  
      strict: false # 严格匹配数据源  
      datasource:  
        master:  
          url: jdbc:mysql://localhost:3306/smart-effect-test?characterEncoding=utf-8&serverTimezone=GMT%2B8&rewriteBatchedStatements=true  
          username: root  
          password: HIKhik@2023  
          driver-class-name: com.mysql.cj.jdbc.Driver  
        vtest:  
          url: jdbc:mysql://localhost:3306/vtest?characterEncoding=utf-8&serverTimezone=GMT%2B8&rewriteBatchedStatements=true  
          username: root  
          password: HIKhik@2023  
          driver-class-name: com.mysql.cj.jdbc.Driver  
  
  redis:  
    host: 10.66.194.60  
    port: 6379  
    database: 2  
    timeout: 43200  
  
mybatis-plus:  
  configuration:  
    #是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN（下划线命名） 到经典 Java 属性名 aColumn（驼峰命名） 的类似映射  
    map-underscore-to-camel-case: true  
    # 日志  
    log-impl: org.apache.ibatis.logging.nologging.NoLoggingImpl  
#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

# 发送邮件

```java

```

## 使用FreeMaker

```java
//构建 Freemarker 的基本配置  
Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);  
// 配置模板位置，启动类的位置JhjmailApplication  
ClassLoader loader = SETApplication.class.getClassLoader();  
configuration.setClassLoaderForTemplateLoading(loader, "templates");  
//加载模板  
Template template = configuration.getTemplate("test-mail.ftl");  
  
TestTemplate testTemplate = new TestTemplate();  
testTemplate.setMailBody("这是邮件内容哈哈哈哈");  
StringWriter out = new StringWriter();  
//模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可  
template.process(testTemplate, out);  
helper.setText(out.toString(),true);  
javaMailSender.send(mimeMessage);
```

# thymeleaf

html文件需要放在resources/templates下，@GetMapping返回值不能加/

    spring:  
      thymeleaf:  
        cache: false  
        prefix: classpath:/templates/

```java
@Controller  
@RequestMapping("/mail")  
public class HelloController {  
    @GetMapping(value = "/hello")  
    public String hello(Model model) {  
        model.addAttribute("msg", "Hello, Thymeleaf!");  
  
        User user = new User();  
        user.setAge(21);  
        user.setName("小明");  
  
        model.addAttribute("user", user);  
        return "index";  
    }  
}
```

index.html

```html
<!DOCTYPE html>  
<html lang="en" xmlns:th="http://www.thymeleaf.org">  
<head>  
    <meta charset="UTF-8">  
    <title>Title</title>  
</head>  
<body>  
<h1 th:text="${msg}">大家好</h1>  
  
<h2>  
    <p>Name: <span th:text="${user.name}">Jack</span>.</p>  
    <p>Age: <span th:text="${user.age}">21</span>.</p>  
</h2>  
</body>  
</html>
```

