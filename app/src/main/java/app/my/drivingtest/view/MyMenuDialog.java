package app.my.drivingtest.view;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import com.socks.library.KLog;
import java.util.List;
import app.my.drivingtest.R;
/**
 * Created by FancyMenG on 2018/3/29.
 */

public class MyMenuDialog extends Dialog {
    private GridView gridView;
    public interface MyMenuListener{
        void setOnMyMenuListener(int position);
    }
    private MyMenuListener myMenuListener;
    public void setOnMyMenuLister(MyMenuListener myMenuListener){
        this.myMenuListener = myMenuListener;
    }
    public MyMenuDialog(@NonNull Context context, List<JageDialogSelctedBean> selectChoice,Context context1) {
        super(context);
        bindView();
        bindData(selectChoice);
    }
    public MyMenuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

    }
    protected MyMenuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    private void bindView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_menu_dialog,null);
        setContentView(view);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        getWindow().setWindowAnimations(R.style.popupwindow_anim_style);
        getWindow().setBackgroundDrawable(null);
        getWindow().setWindowAnimations(R.style.my_menu_dialog_style);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        //设置背景透明度 背景透明
        lp.dimAmount = 0.2f;//参数为0到1之间。0表示完全透明，1就是不透明。按需求调整参数
        getWindow().setAttributes(lp);

        gridView = view.findViewById(R.id.gv_menu);



    }
    private void bindData(List<JageDialogSelctedBean> selectChoice) {

        GridViewAdapter adapter = new GridViewAdapter(getContext(), selectChoice);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KLog.e("hhh");
                if (null!=myMenuListener){
                    myMenuListener.setOnMyMenuListener(position);
                }
            }
        });
    }


}
