package app.my.drivingtest.app.main_resource;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.socks.library.KLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ResourceDetailActivity extends BaseActivity {

    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.loading)
    ImageView loading;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R.id.tv_loading)
    TextView tvLoading;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_resource_detail;
    }

    @Override
    protected void initView() {

        loading.setImageResource(R.drawable.loading);
        final AnimationDrawable mDrawable = (AnimationDrawable) loading.getDrawable();
        mDrawable.start();

        String pdfUrl = "http://www8.cao.go.jp/okinawa/8/2012/0409-1-1.pdf";
        OkHttpUtils//
                .get()//
                .url(pdfUrl)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "g.pdf") {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        int percent = Math.round(progress * 100);
                        tvLoading.setText("正在加载"+percent+"%");
                        KLog.e(progress);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        mDrawable.stop();
                        llLoading.setVisibility(View.GONE);
                        pdfView.fromFile(response)
                                .defaultPage(0)
                                .enableAnnotationRendering(true)
                                .scrollHandle(new DefaultScrollHandle(getApplicationContext()))
                                .load();
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
