/*
SQLyog Ultimate v8.32 
MySQL - 5.5.28 : Database - flyseckill
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`flyseckill` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `flyseckill`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `address_state` varchar(10) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FK_Reference_24` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `address` */

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  `jurisdiction` varchar(10) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_name`) values (1,'数码'),(2,'服饰'),(3,'食品'),(4,'图书');

/*Table structure for table `collect` */

DROP TABLE IF EXISTS `collect`;

CREATE TABLE `collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`collect_id`),
  KEY `FK_Reference_22` (`goods_id`),
  KEY `FK_Reference_23` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `collect` */

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_text` varchar(500) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_Reference_17` (`goods_id`),
  KEY `FK_Reference_18` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`comment_id`,`goods_id`,`user_id`,`comment_text`) values (1,1,1,'手感，外观，很不错。小米系统也很好用很人性化。全面屏很好。机身属于修长类型，但不过分。反正就很合适就对了。音质确实很不错，真的。最大也不破音。很立体很实在。可以购买的。物美价廉！'),(2,2,1,'真的很漂亮，背后有以前索爱的感觉，很喜欢。屏幕护眼，看久了不累，电池续航很好，照相效果不错，本来觉得性能不行，出了10倍变焦版，感觉颜值性能刚好完胜其他手机，'),(3,3,1,'平板尺寸大小合适，重量适中，便于携带，是坐车，旅游首选。做工精良，系统运行流畅！屏幕显示细腻，特别是在高分辨率下。音效很好，不管是高音，低音，还原真实场景。这是买这平板的主要原因。电池给力，用一天没问题。'),(4,4,1,'心仪已久，价格合适的一款（家庭用摄像机），主要是出去旅游时给宝宝录制视频，五轴防抖真的强大，虽然拍摄的时候手会稍微抖动下，但拍摄画面依旧很清晰；20倍的变焦，让成像效果更出色，最主要的是可以直接在摄像机内编辑视频，真的超方便！！'),(5,5,1,'开关机速度都很快，外观大气，送货速度也很快，用了三小时发热不算高，散热也应该不错，我需要装几个大软件，i7完全可以满足我的需求，而且自带win10，开机联网即可激活，很方便。'),(6,6,1,'挺凉爽的布料，不像是平时那种纯棉的，写着纯棉，尺码合适，物流很快。'),(7,7,1,'面料不错，摸起来很舒服，值得购买!'),(8,8,1,'裙子超级好看，超级美丽，好温柔的淡紫色，就是我最爱的颜色呀！！！今天穿上就变小仙女啦！敲开心的！！袖子设计好评哟，把粗手臂给隐藏起来了！完美！'),(9,9,1,'颜色面料都很好，穿着也很舒适，这次遇上活动买的299绝对物超所值，但是裤子尺寸偏大两码，平常穿33码，买了31码才刚刚好。'),(10,10,1,'裤子面料很好，没有色差，没有异味，薄厚适中，摸起来软软的手感好很亲肤，做工精细，没有线头，不会粘毛起球 ，穿上贴身很舒服，时尚百搭 ，做工精细，没有多余的线头，穿在身上很透气，尺码标准 很适合夏天穿'),(11,11,1,'家里一直喝这个水，安全，已经多次购买过，很棒！京东快递的小伙子真棒，每次帮我运上来，还放在我方便的位置，态度非常好，实在难得！'),(12,12,1,'好大一箱，这下爽翻了，追剧有零食吃了。抱枕质量很好，超级可爱，孩子很喜欢。薯条?已经打开吃了，味道不错，日期也很新，信赖京东！'),(13,13,1,'非常好，复购了好多次，喜欢他家的东西，物流快、服务好，商品质量有保障，忠实客户......'),(14,14,1,'好评，好几种口味呢，这几包都没?碎，估计其它的也没有碎，好吃的话会回购追评的。有?吃一包，也可以当做挂面放小青菜鸡蛋?煮着吃。京东自营很方便'),(15,15,1,'儿时的味道，仿佛又回到童年，真的是物美价廉，很划算，味道也一如既往的棒'),(16,16,1,'特意挑了这本书，零基础学起，循序渐进，易懂，学Python编程的不二选择。 彩色的书，看着舒服。还有疑难的知识点的解答码可以扫，挺方便。每章后面都有实战挺好玩'),(17,17,1,' 互联网轻量级整合开发是一本好书，很实用于现在正在工作的it工作者。看了这本书前面的几页就受益匪浅，受益甚多。书本发货物流特别快，快递小哥辛苦了，晚上还在派件。态度也超级好的，给送到门口了，不错，相信京东！'),(18,18,1,'书是真不错 我太喜欢了，给鸟哥点赞，给京东点赞，物流太快了，经常在京东买东西，厉害厉害，好了不说了，说了这么多就是换豆子的哈哈哈哈哈哈哈哈哈哈'),(19,19,1,'非常好的教辅书，老师推荐的效果感觉还不错，程序设计那些比较新颖，很适合刚刚学的是不太懂的同学，很好的质量，京东快递也快'),(20,20,1,'这东西真的是不错，真心是好啊，虽然第一次买，不过真的是物超所值了，非常棒，非常喜欢再JD上买东西，服务好，质量也是非常有保障的。');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `goods_no` varchar(20) NOT NULL,
  `goods_name` varchar(50) NOT NULL,
  `goods_describe` varchar(1000) NOT NULL,
  `original_price` double NOT NULL,
  `current_price` double NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `stock_count` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `FK_Reference_21` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`category_id`,`goods_no`,`goods_name`,`goods_describe`,`original_price`,`current_price`,`image`,`stock_count`,`start_time`,`end_time`) values (1,1,'0101','小米 Redmi K20Pro 4800万超广角三摄 8GB+256GB 碳纤黑 骁龙855 全网通','好手机中国造',2999,20.99,'https://img14.360buyimg.com/n0/jfs/t1/51585/7/2022/166946/5cfe1910Ee43badaf/bdfb7e405b1c8956.jpg',20,'2019-06-11 09:19:55','2019-06-21 09:19:58'),(2,1,'0102','OPPO Reno 10倍变焦版 高通骁龙855 4800万超清三摄 6GB+128GB 薄雾粉 全','好手机中国造',3999,50.99,'https://img14.360buyimg.com/n0/jfs/t1/37238/7/2517/162814/5cb5f20aE5476a0d1/7814f889e23296a6.jpg',50,'2019-06-11 09:48:06','2019-06-21 09:48:10'),(3,1,'0103','微软（Microsoft） 新款Surface Book 2 笔记本平板电脑二合一 13.5英寸I7','垃圾平板美国造',22138,80.9,'https://img14.360buyimg.com/n0/jfs/t30415/362/1601378350/538401/edf1c55/5ce66e98N7c43ffc4.png',20,'2019-06-11 09:49:53','2019-06-21 09:49:56'),(4,1,'0104','索尼（SONY）FDR-AX60 家用/直播4K高清数码摄像机 DV/摄影/录像 5轴防抖 约20倍','就一摄影机，不要钱了',6599,0.3,'https://img12.360buyimg.com/n1/s450x450_jfs/t15928/286/1272262250/143815/9ed54e35/5a4df53eN25076bc7.jpg',5,'2019-06-11 09:51:42','2019-06-21 09:51:45'),(5,1,'0105','外星人Alienware 17.3英寸英特尔酷睿i7游戏笔记本电脑(九代i7-9700K 16G 2','板砖笔记本',28999,60.93,'https://img14.360buyimg.com/n0/jfs/t1/14297/24/14885/100801/5cac30d0Ebe3aebbf/c3553cf44b38aebf.jpg',2,'2019-06-11 09:52:55','2019-06-21 09:52:58'),(6,2,'0201','短袖t恤男装新款夏季印花圆领纯色T恤半袖男士衣服男生潮流青年服饰休闲修身韩版潮流打底衫POLO 82','丑的体恤衫',68,0.68,'https://img11.360buyimg.com/n1/s350x449_jfs/t1/32065/22/9121/239601/5ca389ddEfe66c60b/0d658bd74ca7f532.jpg!cc_350x449.jpg',100,'2019-06-11 09:57:25','2019-06-21 09:57:29'),(7,2,'0202','花花公子短袖衬衫男 中青年夏季清凉免烫修身翻领纯色商务休闲时尚潮流百搭衬衣 XYD015蓝色 175','一件小衬衫',139,1.39,'https://img13.360buyimg.com/n1/s350x449_jfs/t22264/73/188115942/143114/5b37eff6/5b0437d6N302e3cbd.jpg!cc_350x449.jpg',100,'2019-06-11 09:58:25','2019-06-21 09:58:28'),(8,2,'0203','蒋小姐2019夏季新品雪纺连衣裙女短袖韩版修身 套装裙T恤两件套碎花小个子中长款裙子圆领印花 白色1','蒋小姐连衣裙',158,1.58,'https://img11.360buyimg.com/n8/jfs/t1/81769/22/792/465850/5cf013e3Eaefcdda5/5b880b61ef1ad5a0.jpg',30,'2019-06-11 09:59:33','2019-06-21 09:59:37'),(9,2,'0204','吉马克 牛仔裤男2019夏季新款破洞裤男修身小脚潮流九分裤韩版男士浅色大码男装休闲裤子 浅蓝色 29','死亡牛仔裤',139,1.39,'https://item.jd.com/46543258187.html',70,'2019-06-11 10:00:53','2019-06-21 10:00:57'),(10,2,'0205','短裤男新品2019夏季五分裤男休闲沙滩裤男士五分工装短裤学生青年马裤男日系七分运动中裤 K159黑色','比较好看的裤子',108,1.08,'https://item.jd.com/46256587428.html',60,'2019-06-11 10:02:17','2019-06-21 10:02:19'),(11,3,'0301','可口可乐 Coca-Cola 汽水 碳酸饮料 330ml*24罐 整箱装 可口可乐公司出品 摩登罐 ','肥宅快乐水',58.9,5.89,'https://img11.360buyimg.com/n7/jfs/t1/14535/24/12332/341667/5c986e87E30a54403/ef835ab8c733a9af.jpg',300,'2019-06-11 10:03:20','2019-06-21 10:03:24'),(12,3,'0302','乐事（Lay\'s）薯片 零食 休闲食品 颐和园 福禄寿喜财大礼盒 零食大礼包 ','肥宅快乐餐',65,6.5,'https://img11.360buyimg.com/n7/jfs/t29626/293/81284431/244992/58d8135e/5be70fcfN34e0d54e.jpg',500,'2019-06-11 10:04:26','2019-06-21 10:04:30'),(13,3,'0303','卫龙 辣条 休闲零食组合礼包 麻辣 儿时网红小吃 办公室零食大面筋112g*5包','小伙叽，要不要来包辣条',19.5,0.19,'https://img12.360buyimg.com/n7/jfs/t1/40372/21/2592/405697/5cc171a9Eb39ddbd4/a44e8811d3490e6a.jpg',1000,'2019-06-11 10:05:45','2019-06-21 10:05:48'),(14,3,'0304','康师傅方便面红烧牛肉面5袋+香辣牛肉面5袋+鲜虾鱼板面5袋+香菇炖鸡面5袋+老坛酸菜牛肉面5袋 袋装','一碗泡面，一场人生',49.9,4.99,'https://img14.360buyimg.com/n7/jfs/t1/41798/8/4928/437927/5ce6b500Eae9c2cad/b9ab85584128a052.jpg',300,'2019-06-11 10:06:50','2019-06-21 10:06:53'),(15,3,'0305','娃哈哈 整箱包邮 两份立减元儿时怀旧回忆 AD钙奶整箱 哇哈哈儿童牛奶饮料送礼 休闲零食 100ml','李子明童鞋，李子明童鞋，里妈妈拿了两瓶AD流来在学校门口等里。',29.2,2.3,'https://img13.360buyimg.com/n7/jfs/t20185/67/2239256653/267150/a71f01f1/5b508415Na179d205.png',100,'2019-06-11 10:07:38','2019-06-11 10:07:41'),(16,4,'0401','Python3.5编程入门图书 机器学习 数据处理 网络爬虫热门编程语言 从基本概念到完整项目开发 ','Python编程 从入门到入土',62,0.62,'https://img14.360buyimg.com/n1/s200x200_jfs/t17953/201/1450663539/451183/3262b8de/5acb3627N8191c867.jpg',35,'2019-06-11 10:12:28','2019-06-21 10:12:31'),(17,4,'0402','Java EE互联网轻量级框架整合开发 SSM框架（Spring MVC+Spring+MyBati','你学不会的',113.1,1.13,'https://img11.360buyimg.com/n7/jfs/t1/19862/32/5482/345418/5c3fea85E5d15cbf3/95a87b49209f8f7e.jpg',15,'2019-06-11 10:13:31','2019-06-21 10:13:34'),(18,4,'0403','鸟哥的Linux私房菜 基础学习篇 第四版','好像看食谱你就会做菜了一样',80.7,2.33,'https://img11.360buyimg.com/n1/s200x200_jfs/t1/26384/13/1629/419213/5c1463c5E8ae21ae5/9754dfe2533664b6.png',70,'2019-06-11 10:14:42','2019-06-21 10:14:44'),(19,4,'0404','畅销20余年的C++编程入门教程 近百万程序员的C++编程启蒙教程 技术大牛案头常备的工具书 针对C','看英文看英文（破音）',84.2,8.4,'https://img11.360buyimg.com/n1/s200x200_jfs/t5911/45/416956255/119379/cb8ee2c7/5927c874N8db303fe.jpg',20,'2019-06-11 10:15:50','2019-06-21 10:15:53'),(20,4,'0405','全三册黑客攻防从入门到精通+实战篇+绝招版网络安全实战技术知识教程软件工具自学电脑编程入门计算机网','看着看着人没了',141.6,1.6,'https://img12.360buyimg.com/n1/s200x200_jfs/t1/32277/11/13041/190930/5cb941aaE8c4cb19a/171b8e04e881e1ef.jpg',5,'2019-06-11 10:16:39','2019-06-21 10:16:42');

/*Table structure for table `seckill_order` */

DROP TABLE IF EXISTS `seckill_order`;

CREATE TABLE `seckill_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `order_no` varchar(20) NOT NULL,
  `state` varchar(30) NOT NULL,
  `create_time` datetime NOT NULL,
  `pay_time` datetime NOT NULL,
  `address` varchar(100) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_Reference_19` (`user_id`),
  KEY `FK_Reference_20` (`goods_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `seckill_order` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `mailbox` varchar(30) NOT NULL,
  `icon` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `name_index` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`nickname`,`mailbox`,`icon`,`address`) values (1,'13053888876','980609','紫青苏','490548543@qq.com',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
