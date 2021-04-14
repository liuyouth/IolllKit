package com.iolll.kit.FilterBar;

/**
 * Created by LiuBo on 2019-07-04.
 */
public class FilterBarItem extends SelectBean {
    /**
     * 预留标题显示字段
     */
    private String name;

    /**
     * 选中标题颜色
     */
    private int selectColor;
    /**
     * 选中标题右边角标资源
     */
    private int selectDrawable;
    /**
     * 未选中标题颜色
     */
    private int uNSelectColor;
    /**
     * 未选中标题右边角标资源
     */
    private int unSelectDrawable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
