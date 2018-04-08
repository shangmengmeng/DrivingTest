package app.my.drivingtest.app.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jkb.rollinglayout.RollingLayout;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.GlideImageLoader;
import app.my.drivingtest.app.main_banner.BannerDetailActivity;
import app.my.drivingtest.app.main_media.MediaListActivity;
import app.my.drivingtest.app.main_resource.ResourceActivity;
import app.my.drivingtest.app.project.TrainingActivity;
import app.my.mylibrary.utils.ScaleUtils;
import app.my.mylibrary.views.marqueen.UPMarqueeView;

/**
 * Created by FancyMenG on 2018/3/31.
 */

public class MainRecylcerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private LayoutInflater layoutInflater;
    public MainRecylcerAdapter(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
    }
    public static final int BANNER = 0;
    public static final int MAQUEEN = 1;
    public static final int TEST = 2;
    public static final int MEDIA = 3;
    //当前类型
    public int currentType = BANNER;


    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case MAQUEEN:
                currentType = MAQUEEN;
                break;
            case TEST:
                currentType = TEST;
                break;
            case MEDIA:
                currentType = MEDIA;
                break;
        }
        return currentType;
    }
    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==BANNER){
            BannerViewHolder bannerViewHolder = new BannerViewHolder(layoutInflater.inflate(R.layout.main_banner,null));
            return bannerViewHolder;
        }
        else if (viewType ==MAQUEEN){

            MQueenViewHolder mQueenViewHolder = new MQueenViewHolder(layoutInflater.inflate(R.layout.main_maqueen,null),activity);
            return mQueenViewHolder;
        }
        else if (viewType ==TEST){
            View itemView = layoutInflater.inflate(R.layout.main_test,null);

            //解决match parent失效问题
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemView.setLayoutParams(lp);

            TestViewHolder testViewHolder = new TestViewHolder(itemView,activity);
            return testViewHolder;
        }else if (viewType ==MEDIA){
            View itemView = layoutInflater.inflate(R.layout.main_media,null);

            //解决match parent失效问题
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemView.setLayoutParams(lp);

            MediaHolder mediaHolder = new MediaHolder(itemView,activity);
            return mediaHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(activity);
        }else  if (getItemViewType(position) == MAQUEEN) {
            MQueenViewHolder mQueenViewHolderr = (MQueenViewHolder) holder;
            mQueenViewHolderr.setData(activity);
        }else  if (getItemViewType(position) == TEST) {
            TestViewHolder testViewHolder = (TestViewHolder) holder;
            testViewHolder.setData(activity);
        }
        else  if (getItemViewType(position) == MEDIA) {
            MediaHolder mediaHolder = (MediaHolder) holder;
            mediaHolder.setData(activity);
        }
    }
    static class BannerViewHolder extends RecyclerView.ViewHolder{
        protected Banner banner_main;
        public BannerViewHolder(View itemView) {
            super(itemView);
            banner_main = itemView.findViewById(R.id.banner_main);
        }
        public void setData(final Activity activity) {
            ArrayList<Integer> images = new ArrayList<>();
            images.add(R.drawable.icon_banner_3);
            images.add(R.drawable.icon_banner_6);
            images.add(R.drawable.icon_banner_4);
            images.add(R.drawable.icon_banner_2);
            //设置图片加载器
            banner_main.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner_main.setImages(images);
            //设置banner动画效果
            banner_main.setBannerAnimation(Transformer.DepthPage);
            //banner设置方法全部调用完毕时最后调用
            banner_main.start();

           banner_main.setOnBannerListener(new OnBannerListener() {
               @Override
               public void OnBannerClick(int position) {
                   Intent intent = new Intent(activity, BannerDetailActivity.class);
                   activity.startActivity(intent);
               }
           });
        }
    }
    static class MQueenViewHolder extends RecyclerView.ViewHolder{
        private RollingLayout roll_main;
        private Activity activity;
        public MQueenViewHolder(View itemView, Activity activity) {
            super(itemView);
            this.activity =activity;
            roll_main = itemView.findViewById(R.id.roll_main);
        }

        public void setData(final Activity activity) {
            RollingAdapter rollingAdapter = new RollingAdapter(activity);
            roll_main.setAdapter(rollingAdapter);
            roll_main.startRolling();
            roll_main.setRollingEachTime(4000);
            roll_main.setRollingPauseTime(1000);
            roll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }

    static class TestViewHolder extends RecyclerView.ViewHolder{
        protected LinearLayout ll_1,ll_2,ll_3,ll_4,ll_5;
        public TestViewHolder(View itemView, Activity activity) {
            super(itemView);
            ll_1 = itemView.findViewById(R.id.ll_1);
            ll_2 = itemView.findViewById(R.id.ll_2);
            ll_3 = itemView.findViewById(R.id.ll_3);
            ll_4 = itemView.findViewById(R.id.ll_4);
            ll_5 = itemView.findViewById(R.id.ll_5);
        }

        public void setData(final Activity activity) {
            ll_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScaleUtils.getScaleAnimatorSmall(ll_1);
                    Intent intent1 = new Intent(activity, ResourceActivity.class);
                    activity.startActivity(intent1);
                }
            });
            ll_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScaleUtils.getScaleAnimatorSmall(ll_2);
                    Intent intent1 = new Intent(activity, TrainingActivity.class);
                    activity.startActivity(intent1);
                }
            });

            ll_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScaleUtils.getScaleAnimatorSmall(ll_5);
                    Intent intent1 = new Intent(activity, MediaListActivity.class);
                    activity.startActivity(intent1);
                }
            });
        }
    }

    static class MediaHolder extends RecyclerView.ViewHolder{
        private RecyclerView rv_media;
        public MediaHolder(View itemView, Activity activity) {
            super(itemView);
            rv_media = itemView.findViewById(R.id.rv_media);
        }

        public void setData(Activity activity) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,3);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            rv_media.setLayoutManager(gridLayoutManager);
            List<String> list = new ArrayList<>();
            list.add(""); list.add(""); list.add(""); list.add(""); list.add(""); list.add("");//--假数据
            MediaRecyclerAdapter mediaRecyclerAdapter =new MediaRecyclerAdapter(activity,list);
            rv_media.setAdapter(mediaRecyclerAdapter);

        }
    }
}
