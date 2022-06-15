package com.gdupt.hai.domain;

public class Goods {
    private String goodsId;   //商品编号
    private String goodsName;  //商品名称
    private String spec;  //计量单位
    private String measurement; //规格
    private double price;  //价格
    private int cateId;  //分类id
    private String status;  //状态
    private int count;  //数量
    private String goodsLogo;

    public String getGoodsLogo() {
        return goodsLogo;
    }

    public void setGoodsLogo(String goodsLogo) {
        this.goodsLogo = goodsLogo;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", spec='" + spec + '\'' +
                ", measurement='" + measurement + '\'' +
                ", price=" + price +
                ", cateId=" + cateId +
                ", status='" + status + '\'' +
                ", count=" + count +
                ", goodsLogo=" + goodsLogo +
                '}';
    }
}
