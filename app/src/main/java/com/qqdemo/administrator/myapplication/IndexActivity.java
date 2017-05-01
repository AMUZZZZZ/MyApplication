package com.qqdemo.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/30.
 */
public class IndexActivity extends AppCompatActivity {
    @InjectView(R.id.tv_user)
    TextView mTvUser;
    @InjectView(R.id.btn_upload)
    Button mBtnUpload;
    @InjectView(R.id.btn_download)
    Button mBtnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.inject(this);

        User user = MyApplication.getUser();
        mTvUser.setText("欢迎你：" + user.getUser().getUsername());


    }

    @OnClick({R.id.btn_upload, R.id.btn_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                Intent intent=new Intent(this,UploadActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_download:
                Intent intent1=new Intent(this,DownloadActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
