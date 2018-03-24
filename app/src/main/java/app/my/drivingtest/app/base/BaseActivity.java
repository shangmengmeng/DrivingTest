package app.my.drivingtest.app.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by FancyMenG on 2018/3/24.
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    private boolean isActive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);

        initView();
    }

    protected abstract int setLayoutId();

    protected abstract void initView();


    //不带数据的跳转
    public <T extends Activity> void startActivity(Class<T> c) {
        startActivity(new Intent(BaseActivity.this, c));
    }

    //带有数据的跳转
    public <T extends Activity> void startDataActivity(Intent intent, Class<T> c) {
        startActivity(intent);
    }
    //延时跳转
    public <T extends Activity> void delayedStartActivity(final Class<T> c, long time, final boolean finish) {
        Handler han = new Handler();
        han.postDelayed(new Runnable(){

            @Override
            public void run()  {
                startActivity(new Intent(BaseActivity.this, c));
                if (finish) finish();
            }
        }, time);
    }
    //延时跳转附带数据
    public void delayedStartActivity(final Intent in, long time, final boolean finish) {
        Handler han = new Handler();
        han.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(in);
                if (finish) finish();
            }
        }, time);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @Override
    protected void onDestroy() {
        // 结束Activity&从堆栈中移除

        super.onDestroy();
    }


    public boolean isActive() {
        return isActive;
    }
}
