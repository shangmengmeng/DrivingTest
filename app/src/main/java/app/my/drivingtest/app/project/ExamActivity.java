package app.my.drivingtest.app.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import app.my.drivingtest.app.project.fragment.ChooseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamActivity extends BaseActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_me)
    ImageView titleMe;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_exam;
    }

    @Override
    protected void initView() {
        if (null != getIntent().getStringExtra("isFrom")) {
            titleName.setText(getIntent().getStringExtra("isFrom"));
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
