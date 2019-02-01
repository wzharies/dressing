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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_registeractivity_username)   EditText et_username;
    @BindView(R.id.et_registeractivity_password1)  EditText et_password1;
    @BindView(R.id.et_registeractivity_password2)  EditText et_password2;
    @BindView(R.id.et_registeractivity_phoneCodes) EditText et_codes;
    @BindView(R.id.iv_registeractivity_showCode)   ImageView register_code;

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
                    if (password1.equals(password2)) {
                        if (!TextUtils.isEmpty(codes) && realCode.equals(codes)) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            Intent intent2 = new Intent(this, LoginActivity.class);
                            startActivity(intent2);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                            register_code.setImageBitmap(Code.getInstance().createBitmap());
                            realCode = Code.getInstance().getCode().toLowerCase();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
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
