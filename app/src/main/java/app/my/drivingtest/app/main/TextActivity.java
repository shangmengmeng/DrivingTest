package app.my.drivingtest.app.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import app.my.drivingtest.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TextActivity extends AppCompatActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMain.setLayoutManager(linearLayoutManager);
        MainRecylcerAdapter mainRecylcerAdapter = new MainRecylcerAdapter(this);
        rvMain.setAdapter(mainRecylcerAdapter);
    }
}
