package com.qqdemo.administrator.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.qqdemo.administrator.myapplication.MyApplication.IpAddress;

public class DownloadActivity extends AppCompatActivity  {

    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @InjectView(R.id.activity_download)
    RelativeLayout mActivityDownload;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.inject(this);

        onloadData();

    }
    private void onloadData() {
        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("加载数据中···");
        mProgressDialog.show();

        OkGo.get("http://" + IpAddress + ":8080/Login/SelectFormServlet")
                .execute(new StringCallback() {
            @Override
            public void onSuccess(String s, okhttp3.Call call, Response response) {
                FormData formData = new Gson().fromJson(s, FormData.class);
                String result = formData.getResult();
                if (result.equals("null")) {
                    Toast.makeText(DownloadActivity.this, "云端没有文件", Toast.LENGTH_SHORT).show();
                } else {
                    List<FormData.FormDataBean> mPoints = formData.getFormData();

                    MyAdapter mMyAdapter = new MyAdapter(DownloadActivity.this,mPoints);
                    mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    mRecyclerview.setAdapter(mMyAdapter);

                }
                Log.i(TAG, "onSuccess: " + s);
                mProgressDialog.hide();
            }

    });
    }


}
