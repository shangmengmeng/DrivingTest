package app.my.drivingtest.app.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socks.library.KLog;

import java.util.List;

import app.my.drivingtest.R;
import app.my.drivingtest.bean.SelectBean;

/**
 * Created by FancyMenG on 2018/3/27.
 */

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.MyViewHolder> {
    private Context context;
    private List<SelectBean> data ;
    private SelectedNumListener selectedNum;
    public SelectAdapter(Context context ,List<SelectBean> data) {
        this.context = context;
        this.data = data;
    }
    public interface SelectedNumListener {
        void getSelectedNumListener(String letter);
    }
    public void SelectedNumListener (SelectedNumListener selectedNum){
        this.selectedNum = selectedNum;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context ).inflate(R.layout.item_selecter,null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.iv_choose.setSelected(data.get(position).isSelected());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (SelectBean selectBean:data){
//                    selectBean.setSelected(false);
//                }
                for (int i = 0;i<data.size();i++){
                    data.get(i).setSelected(false);
                }
               data.get(position).setSelected(true);
                KLog.e(data);
                notifyDataSetChanged();

                if (null!=selectedNum){
                    String k="";
                    switch (position){
                        case 0:
                            k="A";
                            break;
                        case 1:
                            k="B";
                            break;
                        case 2:
                            k="C";
                            break;
                        case 3:
                            k="D";
                            break;
                        case 4:
                            k="E";
                            break;
                    }

                    selectedNum.getSelectedNumListener(k);
                }


            }
        });
        holder.setData(context,data.get(position),position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        protected ImageView iv_choose;
        protected LinearLayout ll_item;
        protected TextView tv_selectOne;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_choose = itemView.findViewById(R.id.iv_choose);
            ll_item = itemView.findViewById(R.id.ll_item);
            tv_selectOne = itemView.findViewById(R.id.tv_selectOne);
        }
        public void setData(Context context, SelectBean selectBean, int position) {
            tv_selectOne.setText(selectBean.getName());
            switch (position){
                case 0:
                    iv_choose.setImageResource(R.drawable.choose_selector_1);
                    break;
                case 1:
                    iv_choose.setImageResource(R.drawable.choose_selector_2);
                    break;
                case 2:
                    iv_choose.setImageResource(R.drawable.choose_selector_3);
                    break;
                case 3:
                    iv_choose.setImageResource(R.drawable.choose_selector_4);
                    break;
                case 4:
                    iv_choose.setImageResource(R.drawable.choose_selector_5);
                    break;


            }
        }
    }

}
