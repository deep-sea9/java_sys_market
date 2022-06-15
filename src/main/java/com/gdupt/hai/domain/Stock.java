package com.gdupt.hai.domain;

public class Stock {

    private String importBill; //进货单号
    private String goodsName;
    private String goodsId;
    private String proffer;
    private int quantity;
    private double price;
    private double total;
    private String date;

    public String getImportBill() {
        return importBill;
    }

    public void setImportBill(String importBill) {
        this.importBill = importBill;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getProffer() {
        return proffer;
    }

    public void setProffer(String proffer) {
        this.proffer = proffer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "importBill='" + importBill + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", proffer='" + proffer + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
