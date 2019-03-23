package com.example.dress.activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.util.ApiService;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.JsonUser;
import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.util.Map;

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


    public LoginActivity getActivity(){
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_enter,R.id.tv_register,R.id.tv_find_pwd})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enter: {
                login_temp();
                break;
            }
            case R.id.tv_find_pwd: {
                find_pwd();
                break;
            }
            case R.id.tv_register:
                register();
                break;
        }
    }

    protected  void login_temp(){
        User user = new User();

        user.setPhone("18108646556");
        user.setId(100002);
        user.setSex(2);
        user.setSignature("fuck");
        user.setUsername("shit");
        user.setToken("13427467293460");

        user.save();
        cache.setUser(user);

        Intent intent = new Intent(LoginActivity.this,ViewActivity.class);
        startActivity(intent);
        finish();
    }
    protected void login(){
        String phone = et_phone.getText().toString();
        String password = et_pwd.getText().toString();
        JsonObject jsonuser = new JsonObject();
        jsonuser.addProperty("phone",phone);
        jsonuser.addProperty("password",password);

        RetrofitManager.create(ApiService.class).getUser(jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<Map<String,Object>>>(){
                    @Override
                    public void accept(@NonNull ResponseData<Map<String,Object>> rd) throws Exception {
                        if(rd==null){
                            Toast.makeText(LoginActivity.this,"连接不到服务器",Toast.LENGTH_LONG).show();
                            return ;
                        }else if(rd.getRet()==0){
                            Log.i("login",rd.toString());
                            Toast.makeText(LoginActivity.this,rd.getMsg(),Toast.LENGTH_LONG).show();
                            Map<String,Object> map = rd.getData();
                            Log.i("login",map.toString());
                            String token =(String)map.get("token");
                            Log.i("login",token);
                            Log.i("login",map.get("user").toString());
                            Gson gson = new Gson();
                            JsonUser jsonuser = gson.fromJson(map.get("user").toString(),JsonUser.class);
                            User user = new User();

                            user.setPhone(jsonuser.getPhone());
                            user.setId(jsonuser.getId());
                            user.setSex(jsonuser.getSex());
                            user.setSignature(jsonuser.getSignature());
                            user.setUsername(jsonuser.getUsername());
                            user.setToken(token);
                            Log.i("login",user.toString());
                            user.save();
                            cache.setUser(user);

                            Intent intent = new Intent(LoginActivity.this,ViewActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(rd.getRet()==1){
                            Log.i("login",rd.toString());
                            Toast.makeText(LoginActivity.this,rd.getMsg(),Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(LoginActivity.this,"登录",Toast.LENGTH_LONG).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("login",throwable.getMessage());
                    }
                });
    }
    protected void find_pwd(){
        Toast.makeText(this,"findpassword",Toast.LENGTH_SHORT).show();
    }
    protected void register(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}
