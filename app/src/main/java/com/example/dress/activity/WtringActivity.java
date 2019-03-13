package com.example.dress.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dress.R;

public class WtringActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wtring);

        //设置顶部导航栏

        Toolbar replyToolbar = (Toolbar)findViewById(R.id.write_toolBar);
        setSupportActionBar(replyToolbar);
        replyToolbar.setTitle("写信");
        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

    }


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


        }
        return true;
    }
    //发送信息函数
    public void sendMessage(){

    }
}
