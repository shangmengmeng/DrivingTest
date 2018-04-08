package app.my.drivingtest.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;


import app.my.drivingtest.R;


import app.my.drivingtest.app.project.ExamActivity;
import app.my.drivingtest.app.project.TrainingActivity;
import app.my.mylibrary.adapter.recycler.CommonAdapter;
import app.my.mylibrary.adapter.recycler.MultiItemTypeAdapter;
import app.my.mylibrary.adapter.recycler.base.ViewHolder;


/**
 * Created by pig on 2017/4/20.
 * 版本更新的dialog
 */

public class MyDialog extends Dialog {
    private RxPermissions rxPermissions;
    private Activity activity;
    public MyDialog(@NonNull Context context,  Activity activity) {
        super(context);
        this.activity =activity;
        bindView(context);
    }
    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void bindView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_dialog, null);
        setContentView(view);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        getWindow().setWindowAnimations(R.style.popupwindow_anim_style);
        getWindow().setBackgroundDrawable(null);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        //设置背景透明度 背景透明
        lp.dimAmount = 0.2f;//参数为0到1之间。0表示完全透明，1就是不透明。按需求调整参数
        getWindow().setAttributes(lp);


        TextView name = view.findViewById(R.id.dialog_phone_name);
        TextView position = view.findViewById(R.id.dialog_phone_position);

        final ArrayList<String> a = new ArrayList<>();
        a.add("模拟试卷");
        a.add("题库练习");
        RecyclerView rv_phone_dialog = view.findViewById(R.id.rv_phone_dialog);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_phone_dialog.setLayoutManager(manager);
        CommonAdapter<String> adapter = new CommonAdapter<String>(context, R.layout.adapter_my_dialog, a) {
            @Override
            protected void convert(ViewHolder holder, String data, int position) {
                holder.setText(R.id.tv_show_name,data);
            }
        };
        rv_phone_dialog.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (position==0){
                    Intent intent = new Intent(activity, ExamActivity.class);
                    intent.putExtra("isFrom","模拟试卷");
                    activity.startActivity(intent);
                }else {
                    Intent intent = new Intent(activity, TrainingActivity.class);
                    intent.putExtra("isFrom","题库练习");
                    activity.startActivity(intent);
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
    private void makeCall(String num) {

        final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(activity);
        }

    }
}

