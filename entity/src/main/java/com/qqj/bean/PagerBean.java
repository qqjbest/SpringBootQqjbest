package com.qqj.bean;


import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PagerBean<T>{
    // 排序方式
    public enum OrderType
    {
        asc, desc
    }

    private List<T> rows = new ArrayList<T>();// 页面的数据
    private int offset = 0;// 当前页*页面的显示大小
    private int limit = 10;// 页面的显示大小
    private int total = 0;// 总数
    private String sort = "";// 排序字段
    private OrderType order = null;// 排序方式
    private String property;// 查找属性名称
    private String keyword;// 查找关键字，支持模糊查询

    private boolean isPage = true;

    private Map<String, Object> search = new HashMap<String, Object>();

    public void initPage(List<T> data, int total, int limit, int offset)
    {
        this.rows = data;
        this.total = total;
        this.limit = limit;
        this.offset = offset;
    }

    public List<T> getRows()
    {
        return rows;
    }

    public void setRowData(List<T> rows)
    {
        this.rows = rows;
    }

    public int getCurPage()
    {
        return offset / limit + 1;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    /**
     * 获取第几条数据
     */
    public int nextNum()
    {
        return (this.getCurPage() - 1) * this.getLimit();
    }

    /**
     * 取得上一页
     *
     * @return
     */
    public int getPreviousPage()
    {
        return getCurPage() == 1 ? 1 : getCurPage() - 1;
    }

    /**
     * 取得下一页
     *
     * @return
     */
    public int getNextPage()
    {
        return getCurPage() > getTotalPage() ? (getTotalPage() == 0 ? 1 : getTotalPage()) : getCurPage() + 1;
    }

    /**
     * 取得最后一页
     *
     * @return
     */
    public int getLastPage()
    {
        return getTotalPage() == 0 ? 1 : getTotalPage();
    }

    /**
     * 获取页面大小
     *
     * @return
     */
    public int getLimit()
    {
        return limit == 0 ? 10 : limit;
    }

    /**
     * 设置页面显示的大小 例：共几页
     *
     * @param limit
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public void setlimitStr(String rows)
    {
        if (!StringUtils.isEmpty(rows))
            this.limit = Integer.parseInt(rows);
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public int getTotal()
    {
        return total;
    }

    /**
     * 设置总记录数
     *
     * @param total
     */
    public void setTotal(int total)
    {
        this.total = total;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotalPage()
    {
        return this.total == 0 ? 0 : (this.total + this.getLimit() - 1) / this.getLimit();
    }

    /**
     * @return the sortName
     */
    public String getSort()
    {
        return sort;
    }

    /**
     * @param sort
     *            the sortName to set
     */
    public void setSort(String sort)
    {
        this.sort = sort;
    }

    /**
     * @return the sortOrder
     */
    public OrderType getSortOrder()
    {
        return order;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setOrder(String sortOrder)
    {
        try
        {
            this.order = OrderType.valueOf(sortOrder.toLowerCase());
        }
        catch (Exception e)
        {
//            logger.error("非法的排序");
        }
    }

    /**
     * @return the property
     */
    public String getProperty()
    {
        return property;
    }

    /**
     * @param property
     *            the property to set
     */
    public void setProperty(String property)
    {
        this.property = property;
    }

    /**
     * @return the keyword
     */
    public String getKeyword()
    {
        return keyword;
    }

    /**
     * @param keyword
     *            the keyword to set
     */
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    /**
     * @return the searchParams
     */
    public Map<String, Object> getSearch()
    {
        return search;
    }

    /**
     * @param search
     *            the searchParams to set
     */
    public void setSearch(Map<String, Object> search)
    {
        this.search = search;
    }

    /**
     * @return the isPage
     */
    public boolean isPage()
    {
        return isPage;
    }

    /**
     * @param isPage
     *            the isPage to set
     */
    public void setIsPage(String isPage)
    {
        if (isPage != null)
            this.isPage = Boolean.valueOf(isPage);
    }

    public void setRows(Integer rows)
    {
        this.limit = rows;
    }

    public void setUn(String un)
    {
    }
}
