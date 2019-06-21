package com.nwafu.seckill.entity;

public class CommentTemp {
    private Integer goodsId;

    private Integer userId;

    private String commentText;

    private String goodsName;  //商品名称

    private String image;  //商品图片

    private String nickname;

    private String icon;

    public CommentTemp(Integer goodsId, Integer userId, String commentText, String goodsName, String image, String nickname, String icon) {
        this.goodsId = goodsId;
        this.userId = userId;
        this.commentText = commentText;
        this.goodsName = goodsName;
        this.image = image;
        this.nickname = nickname;
        this.icon = icon;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
