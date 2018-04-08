package app.my.drivingtest.app.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import app.my.mylibrary.utils.ScreenUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_me)
    ImageView titleMe;


    ArrayList<Integer> images = new ArrayList<>();


    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    public int mdy;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));

        smartRefreshLayout.setHeaderHeight(80);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        smartRefreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        smartRefreshLayout.finishRefresh(100);//延迟3000毫秒后结束刷新

        ScollLinearLayoutManager linearLayoutManager = new ScollLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMain.setLayoutManager(linearLayoutManager);
        MainRecylcerAdapter mainRecylcerAdapter = new MainRecylcerAdapter(this);
        rvMain.setAdapter(mainRecylcerAdapter);
        View itemView;
        int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (0 < firstPosition || 0 > lastPosition) {
            rvMain.smoothScrollToPosition(0); //滚动到指定的位置
        }

        //滑动监听
        rvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mdy = mdy + dy;
                if (mdy > ScreenUtil.dip2px(MainActivity.this, 20)) {
                    rlTitle.setAlpha(1f);
                }else {
                    rlTitle.setAlpha(mdy * 1.0f / ScreenUtil.dip2px(MainActivity.this, 100));
                }

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
