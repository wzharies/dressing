package com.example.dress.activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.util.ApiService;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.User;
import com.example.dress.util.cache;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
        JsonObject jsonuser = new JsonObject();
        jsonuser.addProperty("username",phone);
        jsonuser.addProperty("password",password);

        System.out.println(jsonuser);

        RetrofitManager.create(ApiService.class).getUser(jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(@NonNull User responseData) throws Exception {
                        Toast.makeText(LoginActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        System.out.println(responseData);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(LoginActivity.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        Toast.makeText(this,"login",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ViewActivity.class);
        startActivity(intent);
    }
    protected void find_pwd(){
        Toast.makeText(this,"findpassword",Toast.LENGTH_SHORT).show();
    }
    protected void register(){
        Toast.makeText(this,"register", Toast.LENGTH_SHORT).show();
    }

}
