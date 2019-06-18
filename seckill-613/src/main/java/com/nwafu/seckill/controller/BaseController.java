package com.nwafu.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于做页面映射跳转的控制层
 * @auther
 * @date 2019/6
 */
@Controller
public class BaseController {

    /**
     * 跳转到秒杀商品页
     *
     * @return
     */
	@RequestMapping("/infor")
    public String testinfor() {
        return "redirect:/seckill/user_center";
    }
	 
    @RequestMapping("/seckill")
    public String seckillGoods() {
        return "redirect:/seckill/list";
    }
}
