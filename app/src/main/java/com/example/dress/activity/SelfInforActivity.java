package com.example.dress.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.util.Api.ApiService;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.JsonUser;
import com.example.dress.util.jsondata.ResponseData;
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
                        .subscribe(new Consumer<ResponseData<Map<String,Object>>>(){
                            @Override
                            public void accept(ResponseData<Map<String,Object>> rd) throws Exception {
                                if(rd==null){
                                    Toast.makeText(SelfInforActivity.this,"连接不到服务器",Toast.LENGTH_SHORT);
                                }else if(rd.getRet()==0){
                                    Map<String,Object> map = rd.getData();
                                    Log.i("updateinfo",map.toString());
                                    Gson gson = new Gson();
                                    JsonUser jsonuser = gson.fromJson(map.toString(),JsonUser.class);
                                    cache.getUser().setUsername(jsonuser.getUsername()).setSignature(jsonuser.getSignature()).setSex(jsonuser.getSex()).save();
                                    Log.i("updateinfo",rd.getMsg());
                                    Toast.makeText(SelfInforActivity.this,rd.getMsg(),Toast.LENGTH_SHORT);
                                    finish();
                                }else if(rd.getRet()==-1){
                                    Log.i("updateinfo",rd.getMsg());
                                    Toast.makeText(SelfInforActivity.this,rd.getMsg(),Toast.LENGTH_SHORT);
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

}
