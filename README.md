# shop-master-springcloud

#### 项目介绍

shop-master-springcloud是一款基于[shop-master](https://github.com/Mack189/shop-master)的商铺点评类APP，此项目对shop-master项目进行了重构，将基于SpringBoot 生态开发的项目重构为一个基于SpringCloud的项目，将原项目拆分为 6 个微服务模块，包括一个消费者和五个服务提供者，此项目在原有技术栈的基础使用了Spring Cloud Alibaba + Nacos + Sentinel + OpenFeign + RabbitMQ等技术

#### 项目功能

- 使用 RabbitMQ 替换基于 Stream 的消息队列

- 使用 Nacos 作为注册中心

- 使用 Sentinel 做流量控制(服务熔断、降级和限流)

- 使用OpenFeign优化远程过程调用

- 使用 Redis 的 Hash 结构保存用户信息，解决登录用户 session 共享的问题

- 封装了一个 Redis 工具类，解决了缓存穿透(缓存空对象)、缓存击穿(逻辑过期+互斥锁)和缓存雪崩(给不同的Key设置TTL)问题
- 基于 Redis 实现了优惠券秒杀功能、并使用 Redis+Lua 脚本来完成秒杀资格判断
- 基于 Redis 的 SortedSet 数据机构实现推模式 Feed 流，以及点赞排行榜功能
- 使用 Redis 的 Geo 和 BitMap 数据结构实现查询附近商铺和用户签到功能
