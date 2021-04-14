package com.iolll.kit.vpFragments.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.iolll.kit.Utils;

import java.util.ArrayList;

/**
 * 当使用viewpager加载Fragment，沉浸式的使用，原理懒加载
 * 添加了MVP结构
 * Created by geyifeng on 2017/4/7.
 * Changed by liubo on 2018/10/10.
 */
public abstract class BaseLazyFragment<V> extends Fragment implements IFragment, LifecycleOwner {

    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        super.onSaveInstanceState(outState);
    }

    View systemBar;
    protected Activity mActivity;
    protected View mRootView;
    public ArrayList<? extends Presenter<V>> presenters;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsPrepare;

    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;

    //    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, mRootView);
        //创建presenter
        presenters = createPresenter();
        ReportFragmentIO.injectIfNeededIn(getActivity());
        //绑定(presenter和View进行绑定)
        if (null != presenters) {
            for (Presenter<V> presenter : presenters) {
                presenter.attachView((V) this);
                presenter.setLifecycleOwner(getLifecycleOwner());
                getLifecycle().addObserver(presenter);
            }

        }
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            initData();
            initView();
            setListener();
            System.out.println(" getArguments() "+getArguments());
            if (getArguments()!=null)
            fragmentReBuild(getArguments());
            if (isImmersionBarEnabled())
                initImmersionBar();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //绑定(presenter和View进行绑定)
        if (null != presenters)
            for (Presenter<V> presenter : presenters) {
                presenter.detachView();
            }

//        if (mImmersionBar != null)
//            mImmersionBar.destroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * 是否懒加载
     *
     * @return the boolean
     */
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisible() {
        onLazyLoad();
    }
    protected void fragmentReBuild(Bundle bundle){};
    private void onLazyLoad() {
        if (mIsVisible && mIsPrepare) {
            mIsPrepare = false;
            initData();
            initView();
            setListener();
            System.out.println(" getArguments() "+getArguments());
            if (getArguments()!=null)
            fragmentReBuild(getArguments());
        }
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected abstract int setLayoutId();

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this)
//                .statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度;
//        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
//        systemBar = mRootView.findViewById(R.id.systemBar);
//        if (null!=systemBar)
//            ImmersionBar.setStatusBarView(getActivity(),systemBar);
    }

    /**
     * view与数据绑定
     */
    protected void initView() {

    }

    /**
     * 设置监听
     */
    protected void setListener() {

    }

    /**
     * 用户不可见执行
     */
    protected void onInvisible() {

    }

    public ArrayList<? extends Presenter<V>> createPresenter() {
        return null;
    }

    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }

    @Override
    public void showToast(int msgId) {
        Utils.toast(msgId);
    }

    @Override
    public void showToast(String msg) {
        Utils.toast(msg);
    }

    public LifecycleOwner getLifecycleOwner() {
        return this;
    }
    
}



/*
 * 因加入lambda 支持导致 此方法类型判断错误 并且无法手动纠正 故采用直接调用静态方法来进行绑定生命周期
 */
//    protected <T> AutoDisposeConverter<T> bindLifecycle() {
//        return RxLifecycleUtils.bindLifecycle(this);
//    }
//    protected <T> AutoDisposeConverter<T> bindLifecycle(Lifecycle.Event event) {
//        return RxLifecycleUtils.bindLifecycle(this,event);
//    }