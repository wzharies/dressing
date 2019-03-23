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
import com.example.dress.util.ApiService;
import com.example.dress.util.Code;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_registeractivity_phone)   EditText et_phone;//手机号码
    @BindView(R.id.et_registeractivity_password1)  EditText et_password1;//密码
    @BindView(R.id.et_registeractivity_password2)  EditText et_password2;//第二次密码
    @BindView(R.id.et_registeractivity_invitation)  EditText et_invitation;//邀请码
    @BindView(R.id.et_registeractivity_phoneCodes) EditText et_codes;//输入的验证码
    @BindView(R.id.iv_registeractivity_showCode)   ImageView register_code;//验证码
    private String realCode;
    private String invitationcode="654321";

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
                String phone = et_phone.getText().toString();
                String password1 = et_password1.getText().toString();
                String password2 = et_password2.getText().toString();
                String invitation = et_invitation.getText().toString();
                String codes = et_codes.getText().toString();
                if (isLegal(phone, password1, password2, invitation, codes)) {
                    JsonObject jsonuser = new JsonObject();
                    jsonuser.addProperty("phone",phone);
                    jsonuser.addProperty("password",password1);
                    RetrofitManager.create(ApiService.class).register(jsonuser)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResponseData<Object>>() {
                                @Override
                                public void accept(@NonNull ResponseData<Object> rs) throws Exception {
                                    if(rs==null){
                                        Toast.makeText(RegisterActivity.this, "连接不到服务器", Toast.LENGTH_LONG).show();
                                    }else if (rs.getRet() == 0) {
                                        Log.i("register",rs.toString());
                                        Toast.makeText(RegisterActivity.this, rs.getMsg(), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, rs.getMsg(), Toast.LENGTH_LONG).show();
                                        register_code.setImageBitmap(Code.getInstance().createBitmap());
                                        realCode = Code.getInstance().getCode().toLowerCase();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    Log.e("register",throwable.getMessage());
                                }
                            });


                }
                break;
            }
            default : {
                break;
            }
        }
    }

    protected boolean isLegal(String phone,String password1,String password2,String invitation,String codes){
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)) {
            if(phone.length()==11&&isNum(phone)) {
                if (password1.equals(password2)) {
                    if (!TextUtils.isEmpty(codes) && realCode.equals(codes)) {
                        if(invitation.equals(invitationcode)) {
                            return true;
                        }else{
                            Toast.makeText(RegisterActivity.this, "邀请码错误", Toast.LENGTH_SHORT).show();
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
        return false;
    }
    private boolean isNum(String phone){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(phone).matches();
    }

}
