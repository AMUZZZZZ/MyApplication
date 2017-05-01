package com.qqdemo.administrator.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.qqdemo.administrator.myapplication.MyApplication.IpAddress;

/**
 * Created by Administrator on 2017/4/30.
 */
public class IndexActivity extends AppCompatActivity {
    private static final String TAG = "11111111111111";
    @InjectView(R.id.tv_user)
    TextView mTvUser;
    @InjectView(R.id.btn_upload)
    Button mBtnUpload;
    @InjectView(R.id.btn_download)
    Button mBtnDownload;
    @InjectView(R.id.tv_num)
    TextView mTvNum;
    @InjectView(R.id.size)
    TextView mSize;
    @InjectView(R.id.ProgressBar)
    ProgressBar mProgressBar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.inject(this);

        User user = MyApplication.getUser();
        mTvUser.setText("欢迎你：" + user.getUser().getUsername());


    }

    private void onloadData() {
        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("加载数据中···");
        mProgressDialog.show();

        OkGo.get("http://" + IpAddress + ":8080/Login/SelectFormServlet").execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                FormData formData = new Gson().fromJson(s, FormData.class);
                String result = formData.getResult();
                if (result.equals("null")) {
                    Toast.makeText(IndexActivity.this, "云端没有文件", Toast.LENGTH_SHORT).show();
                } else {
                    List<FormData.FormDataBean> mPoints = formData.getFormData();
                    mTvNum.setText("云端文件："+mPoints.size()+"个");
                    int sum=0;
                    for(FormData.FormDataBean bean: mPoints){
                        sum+=bean.getFsize();
                    }
                    mSize.setText("云端剩余容量："+sum+"B");
                }
                Log.i(TAG, "onSuccess: " + s);
                mProgressDialog.hide();

            }
        });
    }

    @OnClick({R.id.btn_upload, R.id.btn_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                Intent intent = new Intent(this, UploadActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_download:
                Intent intent1 = new Intent(this, DownloadActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onloadData();
    }
}
