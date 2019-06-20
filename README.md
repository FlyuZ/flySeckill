##配置  
Redis：  
版本3.2.100，使用默认配置  
下载地址：https://github.com/MicrosoftArchive/redis/releases

MySQL5.5   
数据库账号：root  
密码123456
JDK1.8
开发环境：IDEA


首先明确我们的需求，我们要做的是一个单机的练手的项目。这也就意味着我们用不到分布式的东西，主要是为了熟悉技术。然后一般来说，秒杀的商品都不会太多，不会像京东淘宝那样大的商品数量全都拿来秒杀，所以，需要秒杀的商品信息全部放进内存的话是够用的，再一个，秒杀是一个短时高并发的场景，所以一般来说，一旦确定商品数据以后管理员是不会在秒杀进行中修改数据库的。因此所有对商品的库存判断与减库存操作都可以放到内存中进行，创建订单也可以放在内存中进行。

##秒杀的基本流程：  
秒杀列表  
秒杀详情页      
倒计时  
判断用户参加活动的状态     
库存（判断）  
修改库存  
创建订单    
定时任务守护进程  

为什么选择Redis乐观锁来做：  
1、虽然能用数据库的锁避免超卖的问题。但是在大并发的情况下，大大影响数据库性能
2、为了避免并发操作数据库，我们可以使用队列来限制，但是并发量会让队列内存瞬间升高（当然，消息队列是有用的，只是在这个项目中没必要使用）
3、也可以用悲观锁来实现，但是这样会造成用户等待，响应慢体验不好
