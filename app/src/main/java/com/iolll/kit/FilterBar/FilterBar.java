package com.iolll.kit.FilterBar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import com.iolll.kit.FilterBar.Anim.IolllTransitionListener;
import com.iolll.kit.R;
import com.iolll.liubo.ifunction.IFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * 过滤栏
 * Created by LiuBo on 2019-07-04.
 */
public class FilterBar extends ConstraintLayout {
    private Context context;
    private LayoutInflater layoutInflater;
    private ConstraintSet showSet, hideSet;
    /**
     * 遮罩颜色
     */
    private int maskBGColor;
    /**
     * 过滤Bar颜色
     */
    private int filterBarBGColor;
    /**
     * content 内容区域 最大高度
     */
    private int maxHeight;
    /**
     * 存放注册的Class
     */
    private Map<Class, FilterBarViewBinder> map = new HashMap<>();
    /**
     * 多类型数据存放
     */
    private ArrayList<Object> datas = new ArrayList<>();
    /**
     * 标题ViewBinder
     */
    private boolean isShowContent = false;
    private Object old;
    private DeepCopyCallBack deepCopyCallBack;
    private FilterBarViewBinder titleViewBinder;

    public FilterBar(Context context) {
        super(context);
        init(context, null);
    }

    public FilterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private LinearLayout filterTitleBar;
    private LinearLayout filterContent;
    private ConstraintLayout filterBarLayout;
    private View maskView;

    public FilterBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.iolll_filter_bar_layout, this);
        filterTitleBar = findViewById(R.id.filter_title_bar);
        filterContent = findViewById(R.id.filter_content);
        filterBarLayout = findViewById(R.id.filter_bar_layout);
        maskView = findViewById(R.id.mask_view);
        this.context = context;

        showSet = new ConstraintSet();
        hideSet = new ConstraintSet();
        showSet.clone(filterBarLayout);
        showSet.clear(R.id.filter_content_layout);
        showSet.connect(R.id.filter_content_layout, ConstraintSet.TOP, R.id.filter_title_bar, ConstraintSet.BOTTOM);
        showSet.connect(R.id.filter_content_layout, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        showSet.connect(
                com.iolll.kit.R.id.filter_content_layout,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT
        );
        showSet.connect(
                com.iolll.kit.R.id.filter_content_layout,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT
        );
        hideSet.clone(filterBarLayout);
        hideSet.clear(R.id.filter_content_layout);
        hideSet.connect(
                com.iolll.kit.R.id.filter_content_layout, ConstraintSet.BOTTOM,
                R.id.filter_title_bar, ConstraintSet.BOTTOM
        );
        hideSet.connect(
                com.iolll.kit.R.id.filter_content_layout,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT
        );
        hideSet.connect(
                com.iolll.kit.R.id.filter_content_layout,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT
        );

        maskView.setOnClickListener(v -> hideContent(null));
    }

    public <T extends FilterBarItem> void register(Class<? extends T> clazz, @NonNull FilterBarViewBinder<T, ?> binder) {
        map.put(clazz, binder);
    }

    public void addData(Object o) {
        datas.add(o);
    }

    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

    public void notifyDataSetChanged() {
        if (filterTitleBar != null) {
            filterTitleBar.removeAllViews();
            for (int i = 0; i < datas.size(); i++) {
                Object o = datas.get(i);
//                FilterBarViewBinder f = map.get(o.getClass());
                if (null == titleViewBinder)
                    throw new RuntimeException("你必须先调用  setTitleViewBinder 函数 设置title区域的视图解析器");
                if (null != titleViewBinder) {
                    LinearLayout l = new LinearLayout(context);
                    ViewHolder viewHolder = titleViewBinder.onCreateViewHolder(layoutInflater);
                    View v = viewHolder.view;
                    v.setLayoutParams(layoutParams);
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) l.getLayoutParams();
                    if (null == lp)
                        lp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
                    lp.weight = 1;
                    l.setLayoutParams(lp);
                    l.addView(v);
                    titleViewBinder.t = o;
                    l.setOnClickListener(v1 -> selectMenu(o));
                    titleViewBinder.onBindViewHolder(viewHolder, titleViewBinder.t);
                    filterTitleBar.addView(l);
                }

            }
//            if (datas.size() > 0)
//                selectMenu(datas.get(0));
        }

    }

    public void selectMenu(Object o) {
        if (isShowContent()) {

            hideContent(o == old ? null : (IFunction.NullRun) () -> selectMenu(o));
        } else {
            old = o;
            filterContent.removeAllViews();
            FilterBarViewBinder f = map.get(o.getClass());
            if (f == null)
                throw new RuntimeException("你必须先注册" + o.getClass().getSimpleName() + "才能使用");
            ViewHolder vh = f.onCreateViewHolder(layoutInflater);
            Object copy = deepCopyCallBack.deepCopyDone(o);
            f.t = copy;
            f.vh = vh;
            f.onBindViewHolder(f.vh, f.t);
            filterContent.addView(vh.view);
            showContent();
        }

    }

    private void showContent() {
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.addListener(new IolllTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                maskView.setVisibility(VISIBLE);
            }
        });
        autoTransition.setDuration(160);
        TransitionManager.beginDelayedTransition(filterBarLayout, autoTransition);// 动画效果
        showSet.applyTo(filterBarLayout);
        isShowContent = true;
    }

    private void hideContent(IFunction.NullRun nullRun) {
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.addListener(new IolllTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                maskView.setVisibility(INVISIBLE);
                if (nullRun != null)
                    nullRun.run();
            }
        });
        autoTransition.setDuration(160);
        TransitionManager.beginDelayedTransition(filterBarLayout, autoTransition);// 动画效果
        hideSet.applyTo(filterBarLayout);
        isShowContent = false;
    }

    public boolean isShowContent() {
        return isShowContent;
    }

    public void setShowContent(boolean showContent) {
        isShowContent = showContent;
    }

    public int getMaskBGColor() {
        return maskBGColor;
    }

    public void setMaskBGColor(int maskBGColor) {
        this.maskBGColor = maskBGColor;
    }

    public int getFilterBarBGColor() {
        return filterBarBGColor;
    }

    public void setFilterBarBGColor(int filterBarBGColor) {
        this.filterBarBGColor = filterBarBGColor;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setDeepCopyCallBack(DeepCopyCallBack deepCopyCallBack) {
        this.deepCopyCallBack = deepCopyCallBack;
    }

    public Map<Class, FilterBarViewBinder> getMap() {
        return map;
    }

    public void setMap(Map<Class, FilterBarViewBinder> map) {
        this.map = map;
    }

    public ArrayList<Object> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<Object> datas) {
        this.datas = datas;
    }

    public FilterBarViewBinder getTitleViewBinder() {
        return titleViewBinder;
    }

    public void setTitleViewBinder(FilterBarViewBinder titleViewBinder) {
        this.titleViewBinder = titleViewBinder;
    }


    public static class ViewHolder {
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }
    }

    public interface DeepCopyCallBack {
        Object deepCopyDone(@NonNull Object t);
    }
}
