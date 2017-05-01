package com.qqdemo.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.FilePicker;
import okhttp3.Call;
import okhttp3.Response;

import static com.qqdemo.administrator.myapplication.MyApplication.IpAddress;

public class UploadActivity extends AppCompatActivity {

    @InjectView(R.id.activity_upload)
    LinearLayout mActivityUpload;
    @InjectView(R.id.btn_upload)
    Button mBtnUpload;
    @InjectView(R.id.downloadSize)
    TextView mDownloadSize;
    @InjectView(R.id.netSpeed)
    TextView mNetSpeed;
    @InjectView(R.id.tvProgress)
    TextView mTvProgress;
    @InjectView(R.id.ProgressBar)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_upload)
    public void onClick() {
        onFilePicker();

    }

    public void onFilePicker() {
        FilePicker picker = new FilePicker(this, FilePicker.FILE);
        picker.setShowHideDir(false);
        picker.setShowUpDir(true);
        picker.setItemHeight(60);
        picker.setRootPath("storage");
        picker.setOnFilePickListener(new FilePicker.OnFilePickListener() {
            @Override
            public void onFilePicked(String currentPath) {
                Toast.makeText(UploadActivity.this, currentPath, Toast.LENGTH_SHORT).show();

                Upload(currentPath);
            }
        });
        picker.show();
    }

    private void Upload(String currentPath) {
        OkGo.post("http://" + IpAddress + ":8080/Login/UploadServlet")//
                .tag(this)//
                .isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .params("param1", "paramValue1")        // 这里可以上传参数
                .params("file1", new File(currentPath))   // 可以添加文件上传
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Toast.makeText(UploadActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        String downloadLength = Formatter.formatFileSize(getApplicationContext(), currentSize);
                        String totalLength = Formatter.formatFileSize(getApplicationContext(), totalSize);
                        mDownloadSize.setText(downloadLength + "/" + totalLength);
                        String netSpeed = Formatter.formatFileSize(getApplicationContext(), networkSpeed);
                        mNetSpeed.setText(netSpeed + "/S");
                        mTvProgress.setText((Math.round(progress * 10000) * 1.0f / 100) + "%");
                        mProgressBar.setMax(100);
                        mProgressBar.setProgress((int) (progress * 100));
                    }
                });

    }

}
