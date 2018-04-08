package app.my.drivingtest.app.main_resource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import app.my.drivingtest.app.main.MediaRecyclerAdapter;
import app.my.mylibrary.adapter.recycler.CommonAdapter;
import app.my.mylibrary.adapter.recycler.MultiItemTypeAdapter;
import app.my.mylibrary.adapter.recycler.base.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResourceActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_page)
    TextView titlePage;
    @BindView(R.id.title_me)
    ImageView titleMe;
    @BindView(R.id.rv_resource)
    RecyclerView rvResource;



    @Override
    protected int setLayoutId() {
        return R.layout.activity_resource;

    }

    @Override
    protected void initView() {
        titleName.setText("考试资源");

        List<String> list = new ArrayList<>();
        list.add(""); list.add(""); list.add("");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvResource.setLayoutManager(linearLayoutManager);
        CommonAdapter<String> adapter = new CommonAdapter<String>(this,R.layout.item_resource,list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        rvResource.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(ResourceActivity.this,ResourceDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


    }
}
