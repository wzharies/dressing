package com.example.dress.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.util.Api.ApiService;
import com.example.dress.util.Envelope;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.Timestamp;
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

public class WtringActivity extends AppCompatActivity {
    @BindView(R.id.writing_name) EditText writing_name;
    @BindView(R.id.write_view_sender) TextView writing_sender;
 //   @BindView(R.id.send_message)  MenuItem menuItem;
    @BindView(R.id.writing_content) EditText writing_content;
    private String hint = "亲爱的";

    private String receiver;
    private String sender;
    private String text;
    private String timestamp;
    private int receiverid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wtring);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        receiverid = intent.getIntExtra("id",1);
        receiver = intent.getStringExtra("receiver");
        sender = intent.getStringExtra("sender");
        writing_name.setText(hint+receiver+":");
        timestamp = Timestamp.gettime();
        writing_sender.setText(sender+"\n"+ timestamp);

        //设置顶部导航栏

        Toolbar replyToolbar = (Toolbar)findViewById(R.id.write_toolBar);
        setSupportActionBar(replyToolbar);
        replyToolbar.setTitle("写信");
        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

    }
    /*
    @OnClick({R.id.send_message})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_enter: {
                sendMessage();
                break;
            }
            default :{
                break;
            }
        }
    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.send_message:
                sendMessage();
                break;
            case android.R.id.home:
                finish();
                break;


        }
        return true;
    }
    //发送信息函数
    public void sendMessage(){
        text = writing_content.getText().toString();
        Letter letter = new Letter();
        letter.setReceiver(receiver);
        letter.setReceiverid(receiverid);
        letter.setSender(cache.getUser().getUsername());
        letter.setSenderid(cache.getUser().getId());
        letter.setText(text);
        letter.setTimestamp(timestamp);
        letter.setType(0);
        /*
        text = writing_content.getText().toString();
        JsonObject jsonuser = new JsonObject();
        jsonuser.addProperty("receiver",recevier);
        jsonuser.addProperty("sender",sender);
        jsonuser.addProperty("text",text);
        jsonuser.addProperty("timestamp",timestamp);


        RetrofitManager.create(ApiService.class).writingletter(cache.getUser().getToken(),jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<Object>>(){
                    @Override
                    public void accept(@NonNull ResponseData<Object> rd) throws Exception {
                        Toast.makeText(WtringActivity.this,rd.getMsg(),Toast.LENGTH_SHORT);
                        if(rd.getRet()==0){

                            Envelope envelope = new Envelope();
                            envelope.setReceiver(recevier);
                            envelope.setSender(sender);
                            envelope.setText(text);
                            envelope.setTimestamp(timestamp);
                            envelope.setStampViewId(R.drawable.stamp_1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("writingletter",throwable.getMessage());
                    }
                });
                */
        Intent intent = new Intent(this,TempStampViewActivity.class);
        intent.putExtra("letter",letter);
        startActivity(intent);
    }

}
