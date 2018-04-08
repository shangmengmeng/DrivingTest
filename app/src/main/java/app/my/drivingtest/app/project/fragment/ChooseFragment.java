package app.my.drivingtest.app.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.my.drivingtest.R;
import app.my.drivingtest.app.project.adapter.SelectAdapter;
import app.my.drivingtest.bean.SelectBean;
import app.my.drivingtest.bean.SelectNetBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by FancyMenG on 2018/3/26.
 */

public class ChooseFragment extends Fragment {

    @BindView(R.id.rv_choose)
    RecyclerView rvChoose;
    Unbinder unbinder;
    @BindView(R.id.tv_select_title)
    TextView tvSelectTitle;
    private OnResultListener lister;         //用于传递数据

    private SelectNetBean bean;             //list的数据
    private SelectAdapter selectAdapter;    //list的adapter
    private int index;                      //页码

    private List<SelectBean> list;  //选项

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (null != getArguments()) {
            bean = (SelectNetBean) getArguments().getSerializable("indexData");
            index = (int) getArguments().getSerializable("index");

            tvSelectTitle.setText(bean.getTitle());
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvChoose.setLayoutManager(layoutManager);
        //每条选项的数据list
//        List<SelectBean> list = new ArrayList<>();
//        for (int i =0;i<4;i++){
//            SelectBean bean = new SelectBean();
//            bean.setSelected(false);
//            list.add(bean);
//        }
//        KLog.e(bean.getTestContent().size());
        if (null != bean && null != bean.getTestContent()) {

            list = bean.getTestContent();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelected(false);
            }
            selectAdapter = new SelectAdapter(getContext(), list);
            rvChoose.setAdapter(selectAdapter);
        }

        //传递页码
        selectAdapter.SelectedNumListener(new SelectAdapter.SelectedNumListener() {
            @Override
            public void getSelectedNumListener(String letter) {
                if (null != lister) {
                    lister.getStatus(index,letter);
                }

            }
        });
    }


    public interface OnResultListener {
        void getStatus(int pageIndex, String letter);
    }

    public void setOnResultLister(OnResultListener lister) {
        this.lister = lister;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
