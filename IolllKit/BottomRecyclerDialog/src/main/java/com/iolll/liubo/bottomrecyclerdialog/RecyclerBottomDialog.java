package com.iolll.liubo.bottomrecyclerdialog;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.iolll.liubo.iolllviewx.RecyclerView.LinearLayoutManagerX;
import com.iolll.liubo.iolllviewx.RecyclerView.RecyclerViewDivider;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

import static android.text.TextUtils.isEmpty;
import static com.iolll.liubo.niceutil.NiceUtil.isNotNull;


public class RecyclerBottomDialog<T> extends MyBottomSheetDialog {


    @BindView(R2.id.w_bg)
    View wBg;
    @BindView(R2.id.cancel_btn)
    TextView cancelBtn;
    @BindView(R2.id.title_tv)
    TextView titleTv;
    @BindView(R2.id.submit_btn)
    TextView submitBtn;
    @BindView(R2.id.title_layout)
    LinearLayout titleLayout;
    @BindView(R2.id.divider)
    View divider;
    @BindView(R2.id.bottom_list)
    RecyclerView bottomList;
    @BindView(R2.id.myR)
    FrameLayout myR;
    @BindView(R2.id.coordinatir_layout)
    CoordinatorLayout coordinatirLayout;
    private Items items = new Items();
    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private OnCancelListener cancelListener; // 暂时无用
    private OnCancelListener submitListener;
    private boolean mCancelable;
    private Context context;
    private String titleName;
    private String cancelBtnName;
    private String submitBtnName;
    private int listMaxHeight; // 限制列表高度 DP;
    private boolean showBottomBar = false;
    //    private OnItemAllClickListener onItemClick;
    private int titleBG;

    public RecyclerBottomDialog(Builder builder, String s) {
        super(builder.context, builder.mCancelable, builder.cancelListener);
        setmCancelable(builder.mCancelable);
        setBottomList(builder.bottomList);
        setItems(builder.items);
        setAdapter(builder.adapter);
        setCancelListener(builder.cancelListener);
        setmCancelable(builder.mCancelable);
        setContext(builder.context);
//        setListener(builder.listener);
        setTitleName(builder.titleName);
    }



    private RecyclerBottomDialog(Builder builder) {
        super(builder.context);
        setmCancelable(builder.mCancelable);
        setBottomList(builder.bottomList);
        setItems(builder.items);
        setAdapter(builder.adapter);
        setCancelListener(builder.cancelListener);
        setmCancelable(builder.mCancelable);
        setContext(builder.context);
//        setListener(builder.listener);
        setTitleName(builder.titleName);
        setListMaxHeight(builder.listMaxHeight);
        setShowBottomBar(builder.showBottomBar);
//        setOnItemClick(builder.onItemClick);
        setCancelListener(builder.cancelListener);
        submitListener = builder.submitListener;
        cancelBtnName = builder.cancelBtnName;
        submitBtnName = builder.submitBtnName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_dialog_last_time);
        setCancelable(mCancelable);
        ButterKnife.bind(this);
        LinearLayoutManagerX linearLayoutManager = new LinearLayoutManagerX(context);
        linearLayoutManager.setAutoMeasureEnabled(true);
        bottomList.setLayoutManager(linearLayoutManager);
        bottomList.addItemDecoration(new RecyclerViewDivider(context));
        adapter.setItems(items);
        bottomList.setAdapter(adapter);
        setTitleName(titleName);
        if (0 != getListMaxHeight()) {
            ViewGroup.LayoutParams l = bottomList.getLayoutParams();
            l.height = DensityUtils.dp2px(context,getListMaxHeight());
            bottomList.setLayoutParams(l);
        }
        if (titleBG != 0)
            titleLayout.setBackgroundColor(titleBG);
        cancelBtn.setVisibility(View.INVISIBLE);
        submitBtn.setVisibility(View.INVISIBLE);
        if (!mCancelable) {
            cancelBtn.setText("取消");
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            cancelBtn.setVisibility(View.VISIBLE);
        }
        setBtnData(cancelBtn,cancelBtnName,cancelListener);
        setBtnData(submitBtn,submitBtnName,submitListener);
//        if (isNotNull(onItemClick)) {
//            cancelBtn.setOnClickListener(onItemClick.onCancelClick(""));
//            btnCancel.setOnClickListener(onItemClick.onCancelClick(""));
//        }
//        if (isNotNull(onItemClick)) {
//            submitBtn.setOnClickListener(onItemClick.onSubmitClick(""));
//            btnSubmit.setOnClickListener(onItemClick.onSubmitClick(""));
//        }
        wBg.setVisibility(View.GONE);
        if (isShowBottomBar()) {
            wBg.setVisibility(View.VISIBLE);
//            setmBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//                @Override
//                public void onStateChanged(@NonNull View bottomSheet, int newState) {
//
//                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                        cancel();
//                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
//                        bottomLayout.setLayoutParams(setMargin(0, 0, 0, dp2px(226)));
//                    } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
//                        bottomLayout.setLayoutParams(setMargin(0));
//                    }
//                }
//
//                @Override
//                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//                }
//            });
        }


    }

    private void setBtnData(TextView cancelBtn, String cancelBtnName, final OnCancelListener cancelListener) {
        if (!isEmpty(cancelBtnName)){
            cancelBtn.setVisibility(View.VISIBLE);
            cancelBtn.setText(cancelBtnName);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelListener.onCancel(RecyclerBottomDialog.this);
                }
            });
        }
    }


    public void refreshHight() {
        int screenHeight = getScreenHeight(getContext());
        int dialogHeight = screenHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
    }

    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
//        - getStatusBarHeight()
        return height;
    }

    /**
     * 屏幕高度
     *
     * @return
     */

    private int getScreenHeight() {
        return getContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 状态栏高度
     *
     * @return
     */

    private int getStatusBarHeight() {
        int statusBarHeight = 0;
        Resources resources = getContext().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            statusBarHeight = resources.getDimensionPixelSize(resourceId);

        return statusBarHeight;
    }

    public TextView getTitle() {
        return titleTv;
    }

    public void setTitle(TextView title) {
        this.titleTv = title;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
        if (isNotNull(titleTv)) {
            if (isEmpty(titleName)) {
                titleTv.setVisibility(View.GONE);
                titleLayout.setVisibility(View.GONE);
            }
            titleTv.setText(titleName);
        }
    }

    public void setTitleBG(int color) {
        titleBG = color;
        if (isNotNull(titleTv)) {
            titleTv.setBackgroundColor(color);
        }
    }

//    public OnItemAllClickListener<T> getOnItemClick() {
//        return onItemClick;
//    }
//
//    public void setOnItemClick(OnItemClick<T> onItemClick) {
//        this.onItemClick = onItemClick;
//    }
//
//    public OnItemClickListener<SelectDate> getListener() {
//        return listener;
//    }
//
//    public void setListener(OnItemClickListener<SelectDate> listener) {
//        this.listener = listener;
//    }

    public boolean ismCancelable() {
        return mCancelable;
    }

    public void setmCancelable(boolean mCancelable) {
        this.mCancelable = mCancelable;
    }

    public int getListMaxHeight() {
        return listMaxHeight;
    }

    public void setListMaxHeight(int listMaxHeight) {
        this.listMaxHeight = listMaxHeight;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RecyclerView getBottomList() {
        return bottomList;
    }

    public boolean isShowBottomBar() {
        return showBottomBar;
    }

    public void setShowBottomBar(boolean showBottomBar) {
        this.showBottomBar = showBottomBar;
    }

    public void setBottomList(RecyclerView bottomList) {
        this.bottomList = bottomList;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

    }

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    public OnCancelListener getCancelListener() {
        return cancelListener;
    }

    public void setCancelListener(OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
    }


    public static final class Builder<T> {
        private boolean mCancelable;
        private Context context;
        private RecyclerView bottomList;
        private Items items = new Items();
        private MultiTypeAdapter adapter = new MultiTypeAdapter();
        private OnCancelListener cancelListener;
        private OnCancelListener submitListener;
        private String cancelBtnName;
        private String submitBtnName;
        private String titleName;
        private int listMaxHeight = 0;
        private boolean showBottomBar = false;
//        private OnItemClick onItemClick;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder() {
        }

        // 必须传入 context
//        public Builder() {
//        }


        public Builder setMCancelable(boolean val) {
            mCancelable = val;
            return this;
        }


        public Builder setBottomList(RecyclerView val) {
            bottomList = val;
            return this;
        }

        public Builder setItems(Items val) {
            items = val;
            return this;
        }

        public Builder setAdapter(MultiTypeAdapter val) {
            adapter = val;
            return this;
        }

        public Builder setCancelListener(OnCancelListener val) {
            cancelListener = val;
            return this;
        }

        public Builder setSubmitListener(OnCancelListener val) {
            submitListener = val;
            return this;
        }

        public Builder setCancelBtnName(String val) {
            cancelBtnName = val;
            return this;
        }

        public Builder setSubmitBtnName(String val) {
            submitBtnName = val;
            return this;
        }

        public RecyclerBottomDialog build() {
//            if (isNotNull(context, mCancelable, cancelListener))
//                return new HotelBookLastTimeBottomDialog(this, "");
            return new RecyclerBottomDialog(this);
        }

//        public Builder setData(ArrayList<SelectDate> data) {
//            this.items.addAll(data);
//            return this;
//        }
//
//        public Builder setListener(OnItemClickListener<SelectDate> val) {
//            listener = val;
//            return this;
//        }

        public Builder setTitleName(String val) {
            titleName = val;
            return this;
        }

        public Builder setListMaxHeight(int val) {
            listMaxHeight = val;
            return this;
        }

        public Builder setShowBottomBar(boolean val) {
            showBottomBar = val;
            return this;
        }
//
//        public Builder setOnItemClick(OnItemClick<T> val) {
//            onItemClick = val;
//            return this;
//        }
    }

    public RecyclerBottomDialog showDialog() {

        show();
        refreshHight();
        return this;
    }
}
