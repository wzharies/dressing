package com.example.dress.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.util.HttpConnection;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.jsondata.User;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity{
    @BindView(R.id.tv_find_pwd) TextView view_find_pwd;
    @BindView(R.id.tv_register) TextView view_register;
    @BindView(R.id.btn_enter) Button view_btn_enter;
    @BindView(R.id.et_phone) EditText et_phone;
    @BindView(R.id.et_pwd) EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_enter,R.id.tv_register,R.id.tv_find_pwd})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enter:
                login();
                break;
            case R.id.tv_find_pwd:
                find_pwd();
                break;
            case R.id.tv_register:
                register();
                break;
        }
    }

    protected void login(){
        String phone = et_phone.getText().toString();
        String password = et_pwd.getText().toString();
        ResponseData rd = HttpConnection.login(phone,password);
        if(rd==null){
            Toast.makeText(this,"连接不到服务器",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,ViewActivity.class);
            startActivity(intent);
            return ;
        }
        if(rd.getRet()==0){
            cache.setUser((User)rd.getData());
            Toast.makeText(this,"登录陈宫",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,ViewActivity.class);
            startActivity(intent);
            finish();
        }else if(rd.getRet()==1){
            Toast.makeText(this,"密码错误",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this,"登录",Toast.LENGTH_LONG).show();
    }
    protected void find_pwd(){
        Toast.makeText(this,"findpassword",Toast.LENGTH_SHORT).show();
    }
    protected void register(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}
