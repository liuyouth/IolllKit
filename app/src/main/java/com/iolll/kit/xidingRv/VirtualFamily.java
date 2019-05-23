package com.iolll.kit.xidingRv;

/**
 * Created by LiuBo on 2019-05-23.
 */
public interface VirtualFamily {
    /**
     * 是否是吸顶类型
     *
     * @param position
     * @return
     */
    boolean isParentType(int position);
    /**
     * 吸顶类型所包含的子类型的数量
     *
     * @param position
     * @return 最后一个子类型的位置
     */
    int parentChildren(int position);

    /**
     * 是否是子类型
     *
     * @param position
     * @return
     */
    boolean isChildType(int position);

    /**
     * 返回子类型的父类型的位置
     *
     * @param childPosition
     * @return
     */
    int childParentPosition(int childPosition);
}
