package com.iolll.kit.FilterBar.using;

import com.iolll.kit.FilterBar.FilterBarItem;
import com.iolll.kit.FilterBar.SelectBean;

import java.util.ArrayList;

/**
 * 星级价格
 * Created by LiuBo on 2019-07-04.
 */
public class StarPrice extends FilterBarItem {
    public StarPrice(String name) {
        setName(name);
    }

    private ArrayList<SelectBean> selectBeans;
    private int num;

    public ArrayList<SelectBean> getSelectBeans() {
        return selectBeans;
    }

    public void setSelectBeans(ArrayList<SelectBean> selectBeans) {
        this.selectBeans = selectBeans;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
