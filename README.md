### 支持的功能
用户登录、注册、修改个人信息  
购买商品、收藏商品  
购买后评价  
不支持支付功能  

### 开发环境
后端 Springboot + MySQL5.5 + Mybatis + Druid + Redis + SprintbootTask  
前端 Thymeleaf + jQuery  
开发环境：IDEA  

### 安装配置
系统不限  
mysql 5.5+  如需要使用8.0+。请自行修改部分连接配置  
Redis 3.2.100，使用默认配置  
下载地址：https://github.com/MicrosoftArchive/redis/releases  

安装完成后，在数据库中导入sql，然后在IDEA中导入项目

### 运行展示

### 项目难点
如何解决短时高并发的场景，如何解决超卖，缓存和数据库数据一致性问题。
本项目策略是，保证缓存中数据为最新，不保证强一致性，当缓存中数据更新完成后再更新数据库中的数据
