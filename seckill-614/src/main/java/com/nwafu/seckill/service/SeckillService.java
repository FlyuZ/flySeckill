package com.nwafu.seckill.service;

import com.nwafu.seckill.dto.Exposer;
import com.nwafu.seckill.dto.SeckillExecution;
import com.nwafu.seckill.entity.Category;
import com.nwafu.seckill.entity.Comment;
import com.nwafu.seckill.entity.Goods;
import com.nwafu.seckill.entity.SeckillOrder;
import com.nwafu.seckill.enums.SeckillStatEnum;
import com.nwafu.seckill.exception.RepeatKillException;
import com.nwafu.seckill.exception.SeckillCloseException;
import com.nwafu.seckill.exception.SeckillException;
import com.nwafu.seckill.mapper.CategoryMapper;
import com.nwafu.seckill.mapper.CommentMapper;
import com.nwafu.seckill.mapper.GoodsMapper;
import com.nwafu.seckill.mapper.SeckillOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @auther
 * @date 2019/6
 */
@Service
public class SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //设置盐值字符串，随便定义，用于混淆MD5值
    private final String salt = "byfeifei";
    //设置秒杀redis缓存的key
    private final String key = "";

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public List<Goods> findAllGoods() {
        List<Goods> goodsList = redisTemplate.boundHashOps("goods").values();
        if (goodsList == null || goodsList.size() == 0) {
            //说明缓存中没有秒杀列表数据
            //查询数据库中秒杀列表数据，并将列表数据循环放入redis缓存中
            goodsList = goodsMapper.findAll();
            for (Goods goods : goodsList) {
                //将秒杀列表数据依次放入redis缓存中，key:秒杀表的ID值；value:秒杀商品数据
                redisTemplate.boundHashOps("goods").put(goods.getGoodsId(), goods);
                logger.info("商品findAll -> 从数据库中读取放入缓存中");
            }
        } else {
            logger.info("商品findAll -> 从缓存中读取");
        }
        return goodsList;
    }

    public List<Goods> findByCategory(int categoryId) {
        List<Goods> goodsList = goodsMapper.findByCategory(categoryId);
        return goodsList;
    }

    public List<Category> findAllCategory() {
        List<Category> categoryList = redisTemplate.boundHashOps("category").values();
        if (categoryList == null || categoryList.size() == 0) {
            //说明缓存中没有秒杀列表数据
            //查询数据库中秒杀列表数据，并将列表数据循环放入redis缓存中
            categoryList = categoryMapper.findAll();
            for (Category category : categoryList) {
                //将秒杀列表数据依次放入redis缓存中，key:秒杀表的ID值；value:秒杀商品数据
                redisTemplate.boundHashOps("category").put(category.getCategoryId(), category);
                logger.info("分类 -> 从数据库中读取放入缓存中");
            }
        } else {
            logger.info("分类 -> 从缓存中读取");
        }
        return categoryList;
    }

    //根据商品ID查找商品
    public Goods findById(int goodsId) {
        return goodsMapper.findById(goodsId);
    }

    //返回该商品评论
    public List<Comment> findGoodsComment(int goodsId){
        return commentMapper.findByGoodsId(goodsId);
    }

    //返回秒杀接口
    public Exposer exportSeckillUrl(int goodsId) {
        Goods goods = (Goods) redisTemplate.boundHashOps(key).get(goodsId);
        if (goods == null) {
            //说明redis缓存中没有此key对应的value
            //查询数据库，并将数据放入缓存中
            goods = goodsMapper.findById(goodsId);
            if (goods == null) {
                //说明没有查询到
                return new Exposer(false, goodsId);
            } else {
                //查询到了，存入redis缓存中。 key:秒杀表的ID值； value:秒杀表数据
                redisTemplate.boundHashOps(key).put(goods.getGoodsId(), goods);
                logger.info("RedisTemplate -> 从数据库中读取并放入缓存中");
            }
        } else {
            logger.info("RedisTemplate -> 从缓存中读取");
        }
        Date startTime = goods.getStartTime();
        Date endTime = goods.getEndTime();
        //获取系统时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, goodsId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转换特定字符串的过程，不可逆的算法
        String md5 = getMD5(goodsId);
        return new Exposer(true, md5, goodsId);
    }

    //生成MD5值
    private String getMD5(int goodsId) {
        String base = goodsId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    /**
     * 使用注解式事务方法的有优点：开发团队达成了一致约定，明确标注事务方法的编程风格
     * 使用事务控制需要注意：
     * 1.保证事务方法的执行时间尽可能短，不要穿插其他网络操作PRC/HTTP请求（可以将这些请求剥离出来）
     * 2.不是所有的方法都需要事务控制，如只有一条修改的操作、只读操作等是不需要进行事务控制的
     * Spring默认只对运行期异常进行事务的回滚操作，对于编译异常Spring是不进行回滚的，所以对于需要进行事务控制的方法尽可能将可能抛出的异常都转换成运行期异常
     */

    @Transactional
    public boolean executeSeckill(int goodsId, int userId, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMD5(goodsId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑：1.减库存；2.储存秒杀订单
        Date nowTime = new Date();
        try {
            int seckillOrder = seckillOrderMapper.findExist(userId, goodsId);

            if (seckillOrder !=  0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                //减缓存库存，减数据库库存
                System.out.println("执行减库存和缓存操作");
                int updateCount = goodsMapper.reduceStock(goodsId, nowTime);
                if (updateCount <= 0) {
                    //没有更新记录，秒杀结束
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    Goods goods = (Goods) redisTemplate.boundHashOps("goods").get(goodsId);
                    goods.setStockCount(goods.getStockCount() - 1);
                    redisTemplate.boundHashOps("goods").put(goodsId, goods);
                    return true;
                }
            }
        } catch (SeckillCloseException | RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常，转换为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    @Transactional
    public SeckillExecution insertOrder(int userId, int goodsId, String state, String address, double price) {
        Date nowTime = new Date();
        String orderNo = "1010"+ goodsId + userId; //自动生成订单编码
        if (address == null) {
            address = "";
        }
        int res = seckillOrderMapper.insertOrder(userId, goodsId, orderNo, state, nowTime, null, address, price);
        if (res > 0) {
            SeckillOrder seckillOrder = new SeckillOrder(userId, goodsId, orderNo, state, nowTime, null, address, price);
            return new SeckillExecution(goodsId, SeckillStatEnum.SUCCESS, seckillOrder);
        } else {
            return new SeckillExecution(goodsId, SeckillStatEnum.INNER_ERROR, null);
        }
    }
}
