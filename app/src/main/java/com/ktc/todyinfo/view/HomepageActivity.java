package com.ktc.todyinfo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ktc.todyinfo.R;
import com.ktc.todyinfo.widget.adapter.IconAdapter;
import com.ktc.todyinfo.widget.adapter.ItemTouchCallBack;
import com.ktc.todyinfo.model.Icon;
import com.ktc.todyinfo.widget.HeaderBarView;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity implements IconAdapter.OnItemClickListener{

    private HeaderBarView headerBarView;
    private List<Icon> iconList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();
    }

    private void initView() {
        headerBarView = findViewById(R.id.view_header_bar);
        headerBarView.setLeftListener((v) -> {
            Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
        });
        headerBarView.setRightListener((v) -> {
            Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
        });

        for (int i = 0; i < 7; i++) {
            Icon icon = new Icon("5G", R.drawable.ic_baseline_5g_64);
            Icon icon2 = new Icon("4k", R.drawable.ic_baseline_4k_64);
            Icon icon3 = new Icon("3D", R.drawable.ic_baseline_3d_rotation_64);
            Icon icon4 = new Icon("10S", R.drawable.ic_baseline_timer_10_64);
            Icon icon5 = new Icon("filter7", R.drawable.ic_baseline_filter_7_64);
            iconList.add(icon);
            iconList.add(icon2);
            iconList.add(icon3);
            iconList.add(icon4);
            iconList.add(icon5);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        IconAdapter adapter = new IconAdapter(iconList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        ItemTouchCallBack touchCallBack = new ItemTouchCallBack(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View view, int position, Icon icon) {
        Toast.makeText(HomepageActivity.this, icon.getName(), Toast.LENGTH_SHORT).show();
    }
}