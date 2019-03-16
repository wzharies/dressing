package com.example.dress.activity.fragement;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dress.R;
import com.example.dress.activity.SelfInforActivity;


import com.example.dress.activity.ShopActivity;
import com.example.dress.adapter.ActivityCollector;
import com.example.dress.util.cache;


import com.example.dress.adapter.ActivityCollector;
import com.example.dress.util.cache;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment3 extends Fragment {
    @BindView(R.id.page3_1_name) TextView page3_name;
    @BindView(R.id.page3_1_signature)TextView page3_signature;
    @BindView(R.id.page3_1_avatar) ImageView page3_avatar;
    public Fragment3() {
        super();
    }

    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
        return fragment;
    }



    @OnClick({R.id.page3_self,R.id.set,R.id.mydove,R.id.exit,R.id.shop})
    public void OnClick(View view){

        switch (view.getId()){
            case R.id.page3_self:
                //Log.d("tag","here is Toast");
                Intent intent=new Intent(getActivity(), SelfInforActivity.class);

                startActivity(intent);
                break;
            case R.id.mydove:

                Toast.makeText(getActivity(),"mydove",Toast.LENGTH_SHORT).show();
                break;
            case R.id.shop:
                startActivity(new Intent(getActivity(), ShopActivity.class));
                Toast.makeText(getActivity(),"暂未开放",Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(getActivity(),"暂未开放",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                ActivityCollector.finishAll();
                default:
                    break;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragement3, container, false);
        //tx=(TextView) view.findViewById(R.id.page3_1_name);
        ButterKnife.bind(this,view);
        fileload();
        return view;
    }

   public void initselfinfo(){
   //     page3_name.setText(LoginActivity.u.getUsername());
   //     page3_signature.setText(LoginActivity.u.getSignature());
   }

    @Override
    public void onStart() {
        super.onStart();
        initselfinfo();
    }

    protected void fileload(){
        if(cache.getUser().getSex()==1) {
            Glide.with(this)
                    .load(R.drawable.sex1)
                    .into(page3_avatar);
        }else if(cache.getUser().getSex()==2){
            Glide.with(this)
                    .load(R.drawable.sex2)
                    .into(page3_avatar);
        }
        page3_name.setText(cache.getUser().getUsername());
        page3_signature.setText(cache.getUser().getSignature());
    }
}
