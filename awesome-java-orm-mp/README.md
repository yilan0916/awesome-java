# MybaitsPlus

总结MP的使用方法
MP官网 https://baomidou.com/getting-started/


## 项目主要内容

* MP常规的单表CRUD
* 多表查询：
* sql的批量操作
* 加载多数据库
* 代码生成器
* 数据库备份
* mapStruct
* 使用枚举


## CRUD

不要继承Iservice接口，仅使用BaseMapper

## sql的批量操作

https://www.cnblogs.com/liyhbk/p/17218503.html

## 数据库备份

DatabaseBackupConfig，调用此方法+定时器执行即可

## 代码生成器

https://blog.csdn.net/weixin_44783934/article/details/136797435
PO VO: https://blog.csdn.net/TimerBin/article/details/127799360

## 多表查询

> 一对一（User查Dept）、一对多（Dept查User）、多对多（User查Role）
> 其中的Dept和Role也可以使用VO的形式

https://blog.csdn.net/weixin_45529338/article/details/128242184

https://www.altitude.xin/blog/home/#/chapter/306569a46ab9799fa3effc7cd10cbe2d


## 使用枚举

定义表结构的时候经常会碰到一类字段：状态（status）、类型，而通常的做法一般是：
* 数据库中定义tinyint类型
> 比如：status tinyint(1) NOT NULL COMMENT '订单状态 1-待支付;2-待发货;3-待收货;4-已收货;5-已完结;'
* Java实体类中定义Short类型
``` java
order.setStatus((short) 1);

if (order.getStatus() == 1) {
    order.setStatus((short) 2);
}

if (order.getStatus() == 4) {
    order.setStatusName("已收货");
}
```
上面的代码不方便阅读，错误难以排查。所以改进方案是用枚举。

枚举的本质就是 类 + 常量

详情见项目中的Order实体类和OrderMapperTest


https://www.jianshu.com/p/34212407037e


