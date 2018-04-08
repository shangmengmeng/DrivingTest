package app.my.drivingtest.app.main_media;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.githang.statusbar.StatusBarCompat;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.socks.library.KLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import app.my.mylibrary.utils.ScaleUtils;
import app.my.progresslibrary.myviews.DownloadView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Call;

public class MediaDetailActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.vd_media)
    JZVideoPlayerStandard vdMedia;
    @BindView(R.id.tv_media_title)
    TextView tvMediaTitle;
    @BindView(R.id.iv_media_time)
    TextView ivMediaTime;
    @BindView(R.id.iv_media_download)
    ImageView ivDownload;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.dv_progress)
    DownloadView dvProgress;
    private String vdUrl;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_media_detail;
    }

    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        rlTitle.setBackgroundColor(Color.parseColor("#ffffff"));
        titleBack.setVisibility(View.VISIBLE);
        titleName.setTextColor(Color.parseColor("#00b7ee"));
        titleMe.setVisibility(View.GONE);
        vdUrl = "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4";
        vdMedia.setUp(vdUrl
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
//        vdMedia.thumbImageView.setImageResource("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
        //Glide 加载图片简单用法
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH);
        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").apply(options).into(vdMedia.thumbImageView);

        titleBack.setOnClickListener(this);
        ivDownload.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.iv_media_download:
                ScaleUtils.getScaleAnimatorSmall(ivDownload);
                downLoad();
                break;
        }
    }

    private void downLoad() {
        dvProgress.start(new DownloadView.OnCompleteListener() {
            @Override
            public void startComplete() {
                dvProgress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaDetailActivity.this,MediaLocalActivity.class);
                        String url = Environment.getExternalStorageDirectory().getAbsolutePath()+"/g.mp4";
                        intent.putExtra("url",url);
                        startActivity(intent);
                        Toast.makeText(MediaDetailActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        OkHttpUtils//
                .get()//
                .url(vdUrl)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "g.mp4") {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        int percent = Math.round(progress * 100);
                        KLog.e(percent);
                        try {
                            dvProgress.setProgress(percent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onResponse(File response, int id) {

                    }
                });
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
