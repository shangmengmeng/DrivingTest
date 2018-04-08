package app.my.drivingtest.app.main_media;

import android.os.Bundle;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class MediaLocalActivity extends BaseActivity {


    @BindView(R.id.vd_media_local)
    JZVideoPlayerStandard vdMediaLocal;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_media_local;
    }

    @Override
    protected void initView() {
        String url = getIntent().getStringExtra("url");
        vdMediaLocal.setUp(url
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
