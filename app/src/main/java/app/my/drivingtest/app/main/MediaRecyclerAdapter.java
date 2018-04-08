package app.my.drivingtest.app.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.my.drivingtest.R;

/**
 * Created by FancyMenG on 2018/3/31.
 */

public class MediaRecyclerAdapter extends RecyclerView.Adapter<MediaRecyclerAdapter.MediaViewHolder> {
    private Context context;
    private List<String>data;

    public interface OnItemClickListener{
        void setOnClickListener(int Position);
    }
    protected OnItemClickListener listener;

    public void setOnItemClickListner(OnItemClickListener listener){
        this.listener = listener;
    }

    public MediaRecyclerAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_media,null);
        MediaViewHolder holder = new MediaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, final int position) {
        holder.setData(context,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=listener){
                    listener.setOnClickListener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MediaViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_media_icon;
        private TextView tv_media_describe;
        public MediaViewHolder(View itemView) {
            super(itemView);
            iv_media_icon = itemView.findViewById(R.id.iv_media_icon);
            tv_media_describe= itemView.findViewById(R.id.tv_media_describe);
        }

        public void setData(Context context, int position) {

            //Glide 加载图片简单用法
            RequestOptions options = new RequestOptions()
                    .optionalCenterInside()
                    .priority(Priority.HIGH);
            Glide.with(context).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").apply(options).into(iv_media_icon);

        }
    }
}
