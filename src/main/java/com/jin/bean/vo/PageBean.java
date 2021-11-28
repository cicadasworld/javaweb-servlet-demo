package com.jin.bean.vo;

import java.util.List;

/**
 * @author hujin
 * @version 2021/11/10
 */
public class PageBean<T> {

    /** 总条数 */
    private Long totalCount;

    /** 分页数据列表 */
    private List<T> dataList;

    /** 每页条数 */
    private Integer pageSize;

    /** 总页数 */
    private Long totalPage;

    /** 页码（当前页） */
    private Long pageNo;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", dataList=" + dataList +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", pageNo=" + pageNo +
                '}';
    }
}
