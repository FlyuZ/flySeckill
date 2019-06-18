package com.nwafu.seckill.dto;

/**
 * 暴露秒杀地址DTO
 * @auther
 * @date 2019/6
 */
public class Exposer {

    //是否开启秒杀
    private boolean exposed;

    //加密措施，避免用户通过抓包拿到秒杀地址
    private String md5;

    //ID
    private int goodsId;

    //系统当前时间（毫秒）
    private long now;

    //秒杀开启时间
    private long start;

    //秒杀结束时间
    private long end;

    public Exposer(boolean exposed, String md5, int goodsId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.goodsId = goodsId;
    }

    public Exposer(boolean exposed, int goodsId, long now, long start, long end) {
        this.exposed = exposed;
        this.goodsId = goodsId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, int goodsId) {
        this.exposed = exposed;
        this.goodsId = goodsId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", goodsId=" + goodsId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
