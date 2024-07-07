# Redis

* Redis学习笔记
* springboot整合redis
* redis常规用法
* redis提高数据库查询效率


## Redis安装与运行

mac环境：
``` shell
# 查看redis的版本
brew search redis
# 安装最新版
brew install redis
# 启动redis 两种方式
方式1 brew services start redis
方式2
> # 指定配置文件，后台启动必须这样做
> redis-server /usr/local/etc/redis.conf
> # 默认参数启动，这样启动后关闭窗口服务就停止了，不推荐
> redis-server

参考博客：https://blog.csdn.net/weixin_45509705/article/details/119242390
```

## Redis可视化工具

RedisInsight：官方出品

## 命令行操作

redis教程：https://redis.com.cn/redis-keys.html
```shell
# 连接本地的redis服务
redis-cli -h 127.0.0.1 -p 6379

# 关闭redis服务区
redis-cli shutdown

# 使用brew关闭redis
brew services stop redis
```

## springboot整合redis

### 入门案例

依赖
``` xml
<!-- redis缓存 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<!-- 对象池，使用redis时必须引入 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```
yml配置

RedisConfig，@EnableCaching开启缓存

检验Redis环境和配置 RedisTest测试类

redis提高数据库查询效率，UserServiceImpl

参考博客：https://github.com/xkcoding/spring-boot-demo/blob/master/demo-cache-redis/README.md

### 提高

注解：
* @Cacheable，主要针对方法配置，根据请求参数对其结果进行缓存
* @CachePut，主要针对方法配置，能够根据请求参数对其结果进行缓存。和@Cacheable不同的是，它每次都会处罚真实方法的调用
* @CacheEvict，主要针对方法配置，能够根据一定条件对缓存进行清空
* @CacheConfig，用在类上，简化参数配置
> 上面三个参数都有value属性，可以通过@CacheConfig("books")，省略上面参数的value配置
* @Caching，主要针对方法配置，组合多个Cache注解
``` java
// 比如用户新增成功后，我们要添加id–>user；username—>user；email—>user的缓存；此时就需要@Caching组合多个注解标签了。
@Caching(put = {
@CachePut(value = "user", key = "#user.id"),
@CachePut(value = "user", key = "#user.username"),
@CachePut(value = "user", key = "#user.email")
})
public User save(User user) {

}
```


设置过期时间,暂时不支持，可以通过修改配置类
参考：https://blog.csdn.net/lpping90/article/details/117522239

参考博客；https://blog.csdn.net/zzhongcy/article/details/100543263

## RedisUtils

参考：https://blog.csdn.net/qq_53641150/article/details/124180922



