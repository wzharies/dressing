package com.example.dress.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dress.R;
<<<<<<< HEAD
import com.example.dress.util.User;
import com.example.dress.util.cache;
=======
import com.example.dress.activity.fragement.Fragment3;
import com.example.dress.util.ApiService;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
>>>>>>> 898d3cd913f9f914574440cfb6263735343a213b

public class SelfInforActivity extends BaseActivity {
    @BindView(R.id.et_self_username) EditText self_name;
    @BindView(R.id.et_self_signature) EditText self_signature;
    @BindView(R.id.checkBox_1) CheckBox check_1;
    @BindView(R.id.checkBox_2) CheckBox check_2;
    String name=null;
    String signature=null;
    int sex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3_self);
<<<<<<< HEAD
        self_name=(EditText)findViewById(R.id.self_name);
        self_signature=(EditText)findViewById(R.id.self_signature);
        save=(Button)findViewById(R.id.button3);
        //测试使用
       if(cache.getUser()!=null) {
           init(cache.getUser());
       }else{
           Toast.makeText(SelfInforActivity.this,"用户为空",Toast.LENGTH_SHORT).show();
       }

       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cache.getUser().setUsername(self_name.getText().toString());
               cache.getUser().setSignature(self_signature.getText().toString());
             //  LoginActivity.u.setUsername(self_name.getText().toString());
            //   LoginActivity.u.setSignature(self_signature.getText().toString());
               finish();
           }
       });

    }

    public void init(User user){
        self_name.setText(user.getUsername());
        self_signature.setText(user.getSignature());
=======
        ButterKnife.bind(this);
        init();
    }

    @OnClick({R.id.save,R.id.checkBox_1,R.id.checkBox_2})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.save:{
                name = self_name.getText().toString();
                signature = self_signature.getText().toString();
                sex=1;
                if(check_1.isChecked()){
                    sex = 1;
                }else{
                    sex = 2;
                }
                JsonObject json = new JsonObject();
                json.addProperty("id",cache.getUser().getId());
                json.addProperty("username",name);
                json.addProperty("signature",signature);
                json.addProperty("sex",sex);

                RetrofitManager.create(ApiService.class).updateinfo(cache.getUser().getToken(),json)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseData<Object>>(){
                            @Override
                            public void accept(ResponseData<Object> rd) throws Exception {
                                if(rd==null){
                                    Toast.makeText(SelfInforActivity.this,"连接不到服务器",Toast.LENGTH_SHORT);
                                }else if(rd.getRet()==0){
                                    updateinfo();

                                    Log.i("updateinfo",rd.getMsg());
                                    Toast.makeText(SelfInforActivity.this,rd.getMsg(),Toast.LENGTH_SHORT);
                                    Intent intent=new Intent(SelfInforActivity.this, Fragment3.class);
                                    startActivity(intent);
                                    finish();
                                }else if(rd.getRet()==-1){
                                    Log.i("updateinfo",rd.getMsg());
                                    Toast.makeText(SelfInforActivity.this,rd.getMsg(),Toast.LENGTH_SHORT);
                                    Intent intent=new Intent(SelfInforActivity.this, Fragment3.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Log.e("updateinfo",throwable.getMessage());
                            }
                        });
                break;
            }

            case R.id.checkBox_1:{
                check_2.setChecked(false);
                break;
            }

            case R.id.checkBox_2:{
                check_1.setChecked(false);
                break;
            }

            default:{
                break;
            }
        }

    }

    public void init(){
        self_name.setText(cache.getUser().getUsername());
        self_signature.setText(cache.getUser().getSignature());
        if(cache.getUser().getSex()==1){
            check_1.setChecked(true);
        }else if(cache.getUser().getSex()==2){
            check_2.setChecked(true);
        }
    }

    protected void updateinfo(){
        cache.getUser().setUsername(name).setSignature(signature).setSex(sex);
>>>>>>> 898d3cd913f9f914574440cfb6263735343a213b
    }

}
