package com.iolll.kit.FilterBar;

import java.io.Serializable;

/**
 * Created by LiuBo on 2019-07-04.
 */
public class SelectBean implements Serializable {
    /**
     * 是否被选中
     */
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}