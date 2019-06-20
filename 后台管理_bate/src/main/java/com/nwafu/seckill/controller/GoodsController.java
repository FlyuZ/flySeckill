package com.nwafu.seckill.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.nwafu.seckill.entity.Goods;
import com.nwafu.seckill.service.AdminService;
import com.nwafu.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 秒杀商品的控制层
 *
 * @auther
 * @date 2019/6
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @RequestMapping("/goodsManager")
    public String findGoodsList(Model model) {
        int pageNum = 1;
        int pageSize = 5;
        Page<Goods> goods = goodsService.findAll(pageNum, pageSize);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        model.addAttribute("list",pageInfo);
        return "page/goodsManager";
    }
    @PostMapping("/findAll")
    @ResponseBody
    public PageInfo<Goods> findAll(@RequestParam(value = "id") Integer pageNum){
        int  pageSize = 5;
        Page<Goods> goods = goodsService.findAll(pageNum, pageSize);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);

        return pageInfo;
    }


    @ResponseBody
    @PostMapping("/{goodsid}/deleteById")
    public String deleteById(@PathVariable("goodsid") int goodsId){
        goodsService.deleteById(goodsId);
        return "success";
    }

    @ResponseBody
    @PostMapping("/insert")
    public String insert(@RequestParam("categoryid") int categoryid,
                          @RequestParam("goodsno") String goodsno,@RequestParam("goodsname") String goodsname,
                          @RequestParam("goodsdescribe") String goodsdescribe,@RequestParam("originalprice") double originalprice,
                          @RequestParam("currentprice") double currentprice,@RequestParam("image") String image,
                          @RequestParam("stockcount") int stockcount,@RequestParam("starttime") String starttime,
                          @RequestParam("endtime") String endtime){
         goodsService.insert(categoryid,goodsno,goodsname,goodsdescribe, originalprice,
                currentprice,image,stockcount,starttime,endtime);
         return "success";
    }

    @GetMapping("/insertpage")
    public String get_insert(){
        return "page/good_add";
    }

    @PostMapping("/findByName")
    @ResponseBody
    public PageInfo<Goods> findByName(@RequestParam("id") Integer pageNum,
                                      @RequestParam("goods_name") String goodsName){
        int  pageSize = 5;
        Page<Goods> goods = goodsService.findByName(pageNum, pageSize,goodsName);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);

        return pageInfo;
    }

    @PostMapping("/findByTime")
    @ResponseBody
    public PageInfo<Goods> findByTime(@RequestParam("id") Integer pageNum,
                                      @RequestParam("start_time1") Date startTime1,
                                      @RequestParam("start_time2") Date startTime2){
        int  pageSize = 5;

        Page<Goods> goods = goodsService.findByTime(pageNum, pageSize,startTime1,startTime2);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);

        return pageInfo;
    }
}
