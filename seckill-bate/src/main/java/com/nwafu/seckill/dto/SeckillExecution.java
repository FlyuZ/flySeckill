package com.nwafu.seckill.dto;

import com.nwafu.seckill.entity.SeckillOrder;
import com.nwafu.seckill.enums.SeckillStatEnum;

/**
 * 封装执行秒杀后的结果
 *
 * @auther
 * @date 2019/6
 */
public class SeckillExecution {

    private int seckillId;

    //秒杀执行结果状态
    private int state;

    //状态表示
    //stateInfo表示state状态数字的中文解释，比如：秒杀成功、秒杀结束、秒杀系统异常等信息。
    private String stateInfo;

    //秒杀成功的订单对象
    private SeckillOrder seckillOrder;

    public SeckillExecution(int seckillId, SeckillStatEnum seckillStatEnum, SeckillOrder seckillOrder) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.seckillOrder = seckillOrder;
    }

    public SeckillExecution(int seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillOrder getSeckillOrder() {
        return seckillOrder;
    }

    public void setSeckillOrder(SeckillOrder seckillOrder) {
        this.seckillOrder = seckillOrder;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillOrder=" + seckillOrder +
                '}';
    }
}
