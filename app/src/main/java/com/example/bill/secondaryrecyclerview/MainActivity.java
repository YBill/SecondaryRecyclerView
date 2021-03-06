package com.example.bill.secondaryrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter myAdapter;
    private List<DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        myAdapter = new HistoryAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            DataBean bean = new DataBean();
            bean.title = "父 " + (i + 1);

            List<String> childList = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                childList.add("父" + (i + 1) + "下子" + (j + 1));
            }
            bean.list = childList;

            list.add(bean);

        }
    }

}
