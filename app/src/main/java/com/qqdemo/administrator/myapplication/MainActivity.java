package com.qqdemo.administrator.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.qqdemo.administrator.myapplication.MyApplication.IpAddress;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @InjectView(R.id.ed_name)
    EditText mEdName;
    @InjectView(R.id.ed_pwd)
    EditText mEdPwd;
    @InjectView(R.id.btn_login)
    Button mBtnLogin;
    @InjectView(R.id.tv_regist)
    TextView mTvRegist;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mProgressDialog = new ProgressDialog(this);

                mProgressDialog.setMessage("登录中···");
                mProgressDialog.show();
                String url = "http://" + IpAddress + ":8080/Login/loginServlet";
                OkGo.post(url)
                        .params("username", mEdName.getText().toString())
                        .params("password", mEdPwd.getText().toString())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                mProgressDialog.hide();
                                User user = new Gson().fromJson(s, User.class);

                                if (user.getResult().equals("true")) {
                                    MyApplication.setUser(user);
                                    Intent Intent = new Intent(MainActivity.this, IndexActivity.class);
                                    startActivity(Intent);
                                    finish();

                                } else {
                                    Toast.makeText(MainActivity.this, user.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                                ;


                            }
                        });


                break;
            case R.id.tv_regist:
                Intent Intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(Intent);

                break;
        }
    }
}
