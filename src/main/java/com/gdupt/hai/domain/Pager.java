package com.gdupt.hai.domain;

import java.util.List;

public class Pager<T> {

    private int page;//分页起始页
    private int size;//每页记录数
    private List<T> rows;//返回的记录集合
    private int total;//总记录条数

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "page=" + page +
                ", size=" + size +
                ", rows=" + rows +
                ", total=" + total +
                '}';
    }
}
