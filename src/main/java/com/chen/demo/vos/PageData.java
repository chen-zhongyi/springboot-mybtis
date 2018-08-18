package com.chen.demo.vos;

import java.util.List;

public class PageData extends Data {

    private Integer page;
    private Integer size;
    private Integer totalPage;
    private Integer totalSize;

    private List<? extends Object> list;

    public PageData() {
    }

    public PageData(Integer page, Integer size, Integer totalSize, List<? extends OneData> list) {
        this.page = page;
        this.size = size;
        this.totalPage = (totalSize - 1) / size + 1;
        this.totalSize = totalSize;
        this.list = list;
    }

    public PageData(List<? extends OneData> list) {
        this.page = 1;
        this.size = list.size();
        this.totalPage = this.page;
        this.totalSize = this.size;
        this.list = list;
    }
}
