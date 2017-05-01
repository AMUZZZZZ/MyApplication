package com.qqdemo.administrator.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import okhttp3.Call;
import okhttp3.Response;

import static com.qqdemo.administrator.myapplication.MyApplication.IpAddress;

public class RegistActivity extends AppCompatActivity {

    private static final String TAG = "RegistActivity";
    @InjectView(R.id.ed_name)
    EditText mEdName;
    @InjectView(R.id.ed_pwd)
    EditText mEdPwd;
    @InjectView(R.id.ed_repwd)
    EditText mEdRepwd;
    @InjectView(R.id.ed_email)
    EditText mEdEmail;
    @InjectView(R.id.tv_birthday)
    EditText mTvBirthday;
    @InjectView(R.id.btn_register)
    Button mBtnRegister;
    @InjectView(R.id.activity_regist)
    LinearLayout mActivityRegist;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.inject(this);
        mTvBirthday.setInputType(InputType.TYPE_NULL);
        mTvBirthday.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    showDatePicker();
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });
    }

    @OnClick(R.id.btn_register)
    public void onClick(View view) {
        register();
    }

    private void register() {
        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setMessage("注册中···");
        mProgressDialog.show();
        OkGo.post("http://" + IpAddress + ":8080/Login/regServlet")
                .params("username", mEdName.getText().toString())
                .params("password", mEdPwd.getText().toString())
                .params("repassword", mEdRepwd.getText().toString())
                .params("email", mEdEmail.getText().toString())
                .params("birthday", mTvBirthday.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mProgressDialog.hide();
                        RegisterBean registerBean = new Gson().fromJson(s, RegisterBean.class);
                        String result = registerBean.getResult();
                        if (result.equals("true")) {
                            Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent Intent = new Intent(RegistActivity.this, MainActivity.class);
                            startActivity(Intent);
                        }else{
                            String error1= registerBean.getError1();
                            if(error1 !=null){
                                Toast.makeText(RegistActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                            }else{
                                RegisterBean.ErrorBean.MsgBean msg = registerBean.getError().getMsg();
                                String username = msg.getUsername();
                                String password = msg.getPassword();
                                String repassword = msg.getRepassword();
                                String email = msg.getEmail();
                                String birthday = msg.getBirthday();
                                mEdName.setError(username);
                                mEdPwd.setError(password);
                                mEdRepwd.setError(repassword);
                                mEdEmail.setError(email);
                                mTvBirthday.setError(birthday);
                            }
                        }

                    }
                });

    }


    public void showDatePicker() {
        final DatePicker picker = new DatePicker(this);
        picker.setTopPadding(20);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        picker.setRangeStart(year - 100, 1, 1);
        picker.setRangeEnd(year, month, day);
//        picker.setSelectedItem(2050, 10, 14);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                mTvBirthday.setText(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }
}
