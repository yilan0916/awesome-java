# SpringMVC

总结SpringMVC使用方式

## 项目主要内容

*   接口文档管理
*   Restful api接口
*   上传和下载文件
*   参数验证
*   全局异常处理


## 注解总结

### @RequestBody

使用此注解接收参数时，适用于请求体格式为 application/json，只能用对象接收

### @RequestParam

接收的参数是来自HTTP 请求体 或 请求url 的QueryString中
只能用来接收基本数据类型、String 或者MultipartFile类型，不能同时接收请求体中非String(json)和MultipartFile两种类型同传的form-data请求体

### @RequestPart

可以同时上传文件和json（任意）两种格式的form-data请求体

@RequestPart这个注解用在multipart/form-data表单提交请求的方法上。
支持的请求方法的方式MultipartFile，属于Spring的MultipartResolver类。这个请求是通过http协议传输的
利用@RequestPart解决同时上传文件和传递参数问题
功能要求 ：
    post请求方式 后端接收既有实体类对象也有所需上传的文件

实现方式：
@RequestParam 无法接收实体类，只能接收字符串，如果想实现，前端需要将请求实体对象转为字符串，后端用字符串接收，然后后端进行转换处理
@RequestPart可以很好的实现上面的情况

### @PathVariable

URL路径参数  /user/{userId}
PathVariable的解析是按照value(name)属性进行匹配，和URL参数的顺序是无关的 。

## 接口文档管理

技术方案：swagger2+Knife4j，
Knife4jt是对swagger在线接口文档的一个增强
Knife4j官网：<https://doc.xiaominfo.com/>
学习地址：<https://blog.csdn.net/xhmico/article/details/131701790>

```xml
<dependency>  
    <groupId>com.github.xiaoymin</groupId>  
    <artifactId>knife4j-spring-boot-starter</artifactId>  
    <version>3.0.2</version>  
</dependency>
```

Swagger配置类

```java
@Configuration  
@EnableSwagger2  
public class Swagger2Config {  
    /**  
     * 创建一个Docket  
     * @return Docket     
     * @throws MalformedURLException MalformedURLException  
     */    
    @Bean  
    public Docket buildDocket() throws MalformedURLException {  
        RequestParameterBuilder tokenPar = new RequestParameterBuilder();  
        tokenPar.name("Authorization")  
                .description("令牌")  
                .in(ParameterType.HEADER)  
                .required(false)  
                .build();  
        return new Docket(DocumentationType.SWAGGER_2)  
                .groupName("需要认证")  
                .apiInfo(new ApiInfoBuilder()  
                        .title(" API文档")  
                        .description("vtest接口文档，版权所有，请勿盗用，如有抄袭，依法追究法律责任")  
                        .contact(new Contact("zk666", "浙江省杭州市", "zk666@qq.com"))  
                        .version("1.0.0-Alpha")  
                        .build())  
                .select()  
                //要扫描的API(Controller)基础包  
                .apis(basePackages(Arrays.asList("com.abc.rest",  
                        "com.set.user.rest")))  
                .paths(PathSelectors.any())  
                .build()  
                .globalRequestParameters(Arrays.asList(tokenPar.build()));  
    }  
  
    /**  
     * swagger支持多包解析  
     * @param packages packages  
     * @return Predicate<RequestHandler>  
     */  
    private static Predicate<RequestHandler> basePackages(final Collection<String> packages) {  
        return input -> ofNullable(input.declaringClass()).map(handlerPackage(packages)).orElse(true);  
    }  
  
    /**  
     * 解析多个包  
     * @param packages packages  
     * @return Function<Class<?>, Boolean>  
     */    private static Function<Class<?>, Boolean> handlerPackage(final Collection<String> packages) {  
        return input -> {  
            for (String aPackage : packages) {  
                if (ClassUtils.getPackageName(input).startsWith(aPackage)) {  
                    return true;  
                }  
            }            return false;  
        };  
    }  
}
```

# Restful api接口

*   GET /zoos：列出所有动物园

*   POST /zoos：新建一个动物园

*   GET /zoos/ID：获取某个指定动物园的信息

*   PUT /zoos/ID：更新某个指定动物园的信息（提供该动物园的全部信息）

*   PATCH /zoos/ID：更新某个指定动物园的信息（提供该动物园的部分信息）

*   DELETE /zoos/ID：删除某个动物园

*   GET /zoos/ID/animals：列出某个指定动物园的所有动物

*   DELETE /zoos/ID/animals/ID：删除某个指定动物园的指定动物


RestfulApi接口：具体看 ZooController



# 文件上传和下载

具体看 FileController和FileUtils


# 参数验证

学习文档：
<https://blog.csdn.net/weixin_43990804/article/details/112974137>
<https://blog.csdn.net/qq_45737419/article/details/116243216>

校验表单时，使用 @Valid 和 @Validated 都可以。
如果校验失败会抛出 MethodArgumentNotValidException 异常

校验 @RequestParam和 @PathVariable时，校验失败会抛出 ConstraintViolationException 异常

使用@Validated注意事项

    * 如果是@RequestBody传表单，需要@Validated @RequestBody同时使用才生效
    * 如果是@RequestParam或@PathVariable，需要在类上添加@Validated才生效

使用@Valid注意事项

    * 好像只对 @RequestBody失效，其他传参不生效
```xml
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-validation</artifactId>  
</dependency>
```
spring-boot-starter-web包含validation这个包了


# 全局异常处理

具体看GlobalExceptionHandler

# 接口版本管理

<https://juejin.cn/post/7032168742066847781>
<https://www.jianshu.com/p/2c43d15b1675>

## 参考文档

https://www.cnblogs.com/eternityz/p/12442406.html
https://blog.csdn.net/qq_27480007/article/details/136065814