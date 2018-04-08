package app.my.drivingtest.app.main_banner;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerDetailActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_page)
    TextView titlePage;
    @BindView(R.id.title_me)
    ImageView titleMe;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_banner_detail)
    ImageView ivBannerDetail;
    @BindView(R.id.sl_banner_detail)
    SwipeRefreshLayout slBannerDetail;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_banner_detail;
    }

    @Override
    protected void initView() {
        slBannerDetail.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light);
        slBannerDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        slBannerDetail.setRefreshing(false);
                    }
                },1500);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
