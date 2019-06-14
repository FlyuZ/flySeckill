package com.nwafu.seckill.controller;

import com.nwafu.seckill.dto.Exposer;
import com.nwafu.seckill.dto.SeckillExecution;
import com.nwafu.seckill.dto.SeckillResult;
import com.nwafu.seckill.entity.Category;
import com.nwafu.seckill.entity.Goods;
import com.nwafu.seckill.entity.User;
import com.nwafu.seckill.enums.SeckillStatEnum;
import com.nwafu.seckill.exception.RepeatKillException;
import com.nwafu.seckill.exception.SeckillCloseException;
import com.nwafu.seckill.exception.SeckillException;
import com.nwafu.seckill.service.CollectService;
import com.nwafu.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 秒杀商品的控制层
 *
 * @auther
 * @date 2019/6
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private CollectService collectService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/list")
    public String findSeckillList(Model model) {
        List<Goods> list = seckillService.findAllGoods();
        List<Category> categoryList = seckillService.findAllCategory();
        model.addAttribute("list", list);
        model.addAttribute("categoryList", categoryList);
        return "page/goods";
    }

    //进入分类页
    @RequestMapping("/{categoryid}/list")
    public String findCategoryList(@PathVariable("categoryid") int categoryid, Model model) {
        List<Goods> list = seckillService.findByCategory(categoryid);
        List<Category> categoryList = seckillService.findAllCategory();
        model.addAttribute("list", list);
        model.addAttribute("categoryList", categoryList);
        return "page/goods";
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Goods findById(@RequestParam("id") int id) {
        return seckillService.findById(id);
    }

    //进入详情页
    //判断该商品是否被收藏
    @RequestMapping("/{goodsId}/detail")
    public String detail(@PathVariable("goodsId") int goodsId, Model model, HttpServletRequest request) {
        Goods goods = seckillService.findById(goodsId);
        User user = (User) request.getSession().getAttribute("session_user");
        int flag = collectService.findExist(goodsId, user.getUserId());
        model.addAttribute("goods", goods);
        if (flag != 0)
            model.addAttribute("collectFlag", true);
        else
            model.addAttribute("collectFlag", false);
        if (goods == null) {
            return "page/goods";  //没找到，返回首页 ，一般不会出现
        }
        return "page/goods_detail";  //找到了进入详情页
    }
    @ResponseBody
    @PostMapping("/{goodsId}/insertCollect")
    public String insertCollect(@PathVariable("goodsId") int goodsId,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        collectService.insertCollect(goodsId,user.getUserId());
        return "success";
    }
    @ResponseBody
    @PostMapping("/{goodsId}/insertCollect")
    public String deleteCollect(@PathVariable("goodsId") int goodsId,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("session_user");
        collectService.deleteCollect(goodsId,user.getUserId());
        return "success";
    }

    //获取防刷接口
    @ResponseBody
    @RequestMapping(value = "/{goodsId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable("goodsId") int goodsId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(goodsId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{goodsId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("goodsId") int goodsId,
                                                   @PathVariable("md5") String md5,
                                                   HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("session_user");
        if (user == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        Goods goods = (Goods) seckillService.findById(goodsId);
        double price = goods.getCurrentPrice();
        try {
            if (seckillService.executeSeckill(goodsId, user.getUserId(), md5)) {
                SeckillExecution execution = seckillService.insertOrder(user.getUserId(), goodsId,
                        "未支付", user.getAddress(), price);
                return new SeckillResult<SeckillExecution>(true, execution);
            } else {
                return new SeckillResult<SeckillExecution>(false, "未知错误");
            }
        } catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(goodsId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillExecution seckillExecution = new SeckillExecution(goodsId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(goodsId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
    }

    @ResponseBody
    @GetMapping(value = "/time/now")
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }
}
