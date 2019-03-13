package com.example.dress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.util.Code;
import com.example.dress.util.HttpConnection;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.jsondata.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_registeractivity_username)   EditText et_username;//手机号码
    @BindView(R.id.et_registeractivity_password1)  EditText et_password1;//密码
    @BindView(R.id.et_registeractivity_password2)  EditText et_password2;//第二次密码
    @BindView(R.id.et_registeractivity_phoneCodes) EditText et_codes;//输入的验证码
    @BindView(R.id.iv_registeractivity_showCode)   ImageView register_code;//验证码
    private String realCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
        register_code.setImageBitmap(Code.getInstance().createBitmap());
        realCode=Code.getInstance().getCode().toLowerCase();
    }

    @OnClick({R.id.bt_registeractivity_register,R.id.iv_registeractivity_showCode,R.id.iv_registeractivity_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_registeractivity_back:
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
            case R.id.iv_registeractivity_showCode:
                    register_code.setImageBitmap(Code.getInstance().createBitmap());
                    realCode=Code.getInstance().getCode().toLowerCase();
                    break;
            case R.id.bt_registeractivity_register: {
                String username = et_username.getText().toString();
                String password1 = et_password1.getText().toString();
                String password2 = et_password2.getText().toString();
                String codes = et_codes.getText().toString();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)) {
                    if(username.length()==11) {
                        if (password1.equals(password2)) {
                            if (!TextUtils.isEmpty(codes) && realCode.equals(codes)) {
                                ResponseData<User> rs = HttpConnection.register(username, password1);
                                if (rs.getRet() == 0) {
                                    cache.setUser(rs.getData());
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                                register_code.setImageBitmap(Code.getInstance().createBitmap());
                                realCode = Code.getInstance().getCode().toLowerCase();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default :
                break;
        }
    }
}
