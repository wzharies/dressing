package com.example.dress.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dress.R;

public class SelfInforActivity extends BaseActivity {

    private EditText self_name;
    private EditText self_signature;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3_self);
        self_name=(EditText)findViewById(R.id.self_name);
        self_signature=(EditText)findViewById(R.id.self_signature);
        save=(Button)findViewById(R.id.button3);
       /* Intent intent=getIntent();
        String data=intent.getStringExtra("data");
        self_name.setText(data);*/
       init();
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               LoginActivity.u.setUsername(self_name.getText().toString());
               LoginActivity.u.setSignature(self_signature.getText().toString());
               finish();
           }
       });

    }

    public void init(){
        self_name.setText(LoginActivity.u.getUsername());
        self_signature.setText(LoginActivity.u.getSignature());
    }

}
