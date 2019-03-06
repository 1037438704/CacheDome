package com.example.administrator.cachedome;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.cachedome.adp.DownAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.DbDownUtil;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<DownInfo> listData;
    DbDownUtil dbUtil;
    private Button button;

    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "aaaaaaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        Log.d("zdl", "====================日志已启动======================");
        Log.d("zdl", "====================日志已启动======================" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        initResource();
        initWidget();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidget() {
        EasyRecyclerView recyclerView = (EasyRecyclerView) findViewById(R.id.rv);
        DownAdapter adapter = new DownAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.addAll(listData);
    }

    private void initResource() {
        dbUtil = DbDownUtil.getInstance();
        listData = dbUtil.queryDownAll();
        /*第一次模拟服务器返回数据掺入到数据库中*/
        Log.d("zdl", "====================日志已启动======================");


        if (listData.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                File outputFile = new File(path,
                        "test" + i + ".mp4");
//                File outputFile = new File(path,
//                        "test" + i + ".mp4");


                Log.d("zdl", "========================" + i + "=========getPath=========" + outputFile.getPath());
                Log.d("zdl", "========================" + i + "==========getAbsolutePath========" + outputFile.getAbsolutePath());

                DownInfo apkApi = new DownInfo("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
                apkApi.setId(i);
                apkApi.setUpdateProgress(true);
                apkApi.setSavePath(outputFile.getAbsolutePath());
                dbUtil.save(apkApi);
            }
            listData = dbUtil.queryDownAll();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         /*记录退出时下载任务的状态-复原用*/
        for (DownInfo downInfo : listData) {
            dbUtil.update(downInfo);
        }
    }
}
