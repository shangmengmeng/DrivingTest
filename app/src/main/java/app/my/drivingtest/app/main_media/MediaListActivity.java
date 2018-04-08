package app.my.drivingtest.app.main_media;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import app.my.drivingtest.app.main.MainActivity;
import app.my.drivingtest.app.main_resource.ResourceActivity;
import app.my.drivingtest.app.main_resource.ResourceDetailActivity;
import app.my.mylibrary.adapter.recycler.CommonAdapter;
import app.my.mylibrary.adapter.recycler.MultiItemTypeAdapter;
import app.my.mylibrary.adapter.recycler.base.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaListActivity extends BaseActivity {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_page)
    TextView titlePage;
    @BindView(R.id.title_me)
    ImageView titleMe;
    @BindView(R.id.xrv_media)
    XRecyclerView xrvMedia;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_media_list;
    }

    @Override
    protected void initView() {

        List<String> list = new ArrayList<>();
        list.add(""); list.add(""); list.add("");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvMedia.setLayoutManager(linearLayoutManager);
        xrvMedia.setLoadingMoreEnabled(false);
        xrvMedia.setPullRefreshEnabled(true);
        xrvMedia
                .getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        CommonAdapter<String> adapter = new CommonAdapter<String>(this,R.layout.item_media_list,list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
              ImageView imageView = holder.getConvertView().findViewById(R.id.iv_media_list);
                //Glide 加载图片简单用法
                RequestOptions options = new RequestOptions()
                        .optionalCenterInside()
                        .priority(Priority.HIGH);
                Glide.with(MediaListActivity.this).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").apply(options).into(imageView);

            }
        };
        xrvMedia.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(MediaListActivity.this,MediaDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        xrvMedia.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrvMedia.refreshComplete();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                // load more data here
            }
        });
    }


}
