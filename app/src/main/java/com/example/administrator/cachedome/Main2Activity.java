package com.example.administrator.cachedome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.cachedome.adp.RVAdp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RVAdp rvAdp;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdp = new RVAdp(R.layout.item_recyclerview);
        recyclerView.setAdapter(rvAdp);
        List<String> filesAllName = getFilesAllName(MainActivity.path);
        for (int i = 0; i < filesAllName.size(); i++) {
            Log.d("zdl", "==========获取到的==========" + filesAllName);
        }
        rvAdp.setNewData(filesAllName);
        rvAdp.notifyDataSetChanged();
        rvAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("phth",rvAdp.getData().get(position));
                startActivity(intent);
            }
        });
    }

    public static List<String> getFilesAllName(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) {
            Log.e("error", "空目录");
            return null;
        }
        List<String> s = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            s.add(files[i].getAbsolutePath());
            Log.d("zdl", "=============集合中的路径===============" + files[i].getAbsolutePath());
        }
        return s;
    }
}
