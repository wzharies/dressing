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
import com.example.dress.adapter.ActivityCollector;
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

public class WtringActivity extends BaseActivity {
    @BindView(R.id.writing_name) EditText writing_name;
    @BindView(R.id.write_view_sender) TextView writing_sender;
 // @BindView(R.id.send_message)  MenuItem menuItem;
    @BindView(R.id.writing_content) EditText writing_content;
 // private String hint = "亲爱的";
    private int sendertype;
    //private String receiver;
    private String sender;
    private String text;
    private String timestamp;
    private int receiverid;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wtring);
        ActivityCollector.addStampActivity(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        sendertype = intent.getIntExtra("type",0);
        if(sendertype==0) {
            receiverid = intent.getIntExtra("id", 1);
            //    receiver = intent.getStringExtra("receiver");
            //    sender = intent.getStringExtra("sender");
            sender = cache.getUser().getUsername();
            writing_name.setText("Id:"+receiverid+"");
        }else if(sendertype ==1){                  //发送给陌生人
            receiverid = -1;
            sender = cache.getUser().getUsername();
            writing_name.setText("亲爱的陌生人");
        }else if(sendertype ==2){                 //发送给自己
            receiverid = cache.getUser().getId();
            sender = cache.getUser().getUsername();
            year = intent.getIntExtra("year",2077);
            month = intent.getIntExtra("month",0);
            day = intent.getIntExtra("day",0);
            Log.i(year+month+day+"wtringactivity",year+month+day+"");
        }
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
        letter.setReceiver(writing_name.getText().toString());
        letter.setReceiverid(receiverid);
        letter.setSender(cache.getUser().getUsername());
        letter.setSenderid(cache.getUser().getId());
        letter.setText(text);
        letter.setTimestamp(timestamp);
        letter.setType(sendertype);
        Intent intent = new Intent(this, TempStampViewActivity.class);
        if(sendertype==2) {
            intent.putExtra("year",year);
            intent.putExtra("month",month);
            intent.putExtra("day",day);
        }
        intent.putExtra("letter", letter);
        startActivity(intent);
    }

}
