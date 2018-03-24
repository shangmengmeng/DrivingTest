package app.my.drivingtest.app.login;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import com.githang.statusbar.StatusBarCompat;
import app.my.drivingtest.MainActivity;
import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import app.my.mylibrary.utils.ScaleUtils;
import butterknife.BindView;

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_splash_in)
    ImageView ivSplashIn;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        ivSplashIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_splash_in:
                ScaleUtils.getScaleAnimatorSmall(ivSplashIn);
                startActivity(MainActivity.class);
                break;
        }
    }
}
