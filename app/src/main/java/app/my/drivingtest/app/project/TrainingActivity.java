package app.my.drivingtest.app.project;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import app.my.drivingtest.R;
import app.my.drivingtest.app.base.BaseActivity;
import app.my.drivingtest.app.project.fragment.ChooseFragment;
import app.my.drivingtest.bean.SelectBean;
import app.my.drivingtest.bean.SelectNetBean;
import app.my.drivingtest.view.JageDialogSelctedBean;
import app.my.drivingtest.view.MyMenuDialog;
import app.my.mylibrary.utils.ScaleUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingActivity extends BaseActivity implements ChooseFragment.OnResultListener, View.OnClickListener,MyMenuDialog.MyMenuListener {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_me)
    ImageView titleMe;
    @BindView(R.id.vp_choose)
    ViewPager vpChoose;
    @BindView(R.id.title_page)
    TextView titlePage;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    private String[] a;
    private int currentIndex;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        if (null != getIntent().getStringExtra("isFrom")) {
            titleName.setText(getIntent().getStringExtra("isFrom"));
        }
        titleMe.setVisibility(View.GONE);
        titlePage.setVisibility(View.VISIBLE);

        //---此处应有数据
        ArrayList<SelectNetBean> data = new ArrayList<>();
        SelectNetBean bean = new SelectNetBean();
        ArrayList<SelectBean> list = new ArrayList<SelectBean>();
        SelectBean bean1 = new SelectBean();
        bean1.setName("减速、观察、慢通过");
        SelectBean bean2 = new SelectBean();
        bean2.setName("加速，尽快通过");
        SelectBean bean3 = new SelectBean();
        bean3.setName("挤靠“加塞”车辆，逼其离开");
        SelectBean bean4 = new SelectBean();
        bean3.setName("加塞”车辆，逼其离开");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);

        bean.setTestContent(list);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);
        data.add(bean);

        a = new String[data.size()];
        final ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ChooseFragment chooseFragment = new ChooseFragment();
            //将数据传递给fragment；（包括页码从0开始）
            Bundle bundle = new Bundle();
            bundle.putSerializable("indexData", data.get(i));
            bundle.putSerializable("index", i);
            chooseFragment.setArguments(bundle);


            chooseFragment.setOnResultLister(TrainingActivity.this);
            fragments.add(chooseFragment);
        }


        //初始化页码
        String page = 1 + "/" + fragments.size();
        titlePage.setText(page);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {

                return fragments.get(position);
            }
        };
        vpChoose.setAdapter(adapter);
        vpChoose.setOffscreenPageLimit(100);

        vpChoose.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String page = position + 1 + "/" + fragments.size();
                titlePage.setText(page);
                currentIndex = position;
                if (position == fragments.size() - 1) {
                    tvSubmit.setVisibility(View.VISIBLE);
                } else {
                    tvSubmit.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvSubmit.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //从fragment传过来的页码和字母选项
    @Override
    public void getStatus(int pageIndex, String letter) {
        Toast.makeText(this, "pageIndex:" + pageIndex, Toast.LENGTH_SHORT).show();

        a[pageIndex] = letter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //提交
            case R.id.tv_submit:
                ScaleUtils.getScaleAlphaAnimatorSmall(tvSubmit);

                for (int i = 0; i < a.length; i++) {
                    KLog.e(a[i]);
                    if (null==a[i]){
                        Toast.makeText(this, "第" + i + "项没有作答", Toast.LENGTH_SHORT).show();
                        vpChoose.setCurrentItem(i);
                    }
                }
                break;
            //菜单
            case R.id.iv_menu:
                KLog.e(a.length+"iiiii");

                List<JageDialogSelctedBean> selectChoice =new ArrayList<>();

                for (int i = 0; i < a.length; i++) {
                    JageDialogSelctedBean s=new JageDialogSelctedBean();
                    if (null!=a[i]){
                        s.setSelectStatus(a[i]);
                        s.setSelected(true);
                    }else {
                        s.setSelectStatus("");
                        s.setSelected(false);
                    }
                    selectChoice.add(s);
                    s=null;
                }
                MyMenuDialog myMenuDialog = new MyMenuDialog(this,selectChoice,this);
                myMenuDialog.setOnMyMenuLister(this);
                myMenuDialog.show();

                break;
        }
    }

    @Override
    public void setOnMyMenuListener(int position) {
        vpChoose.setCurrentItem(position);
    }
}
