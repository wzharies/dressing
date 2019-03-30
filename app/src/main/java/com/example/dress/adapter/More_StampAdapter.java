package com.example.dress.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dress.R;
import com.example.dress.activity.More_StampActivity;
import com.example.dress.activity.WtringActivity;
import com.example.dress.util.Api.ApiService;
import com.example.dress.util.GetResouce;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.Stamp.tempStamp;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

public class More_StampAdapter extends RecyclerView.Adapter<More_StampAdapter.ViewHolder> {
    private Context mContext;
    private int position;
    private int[] stampcount;
    private List<tempStamp> stamps;
    private Letter letter;

    private int year;
    private int month;
    private int day;

    public More_StampAdapter(int _position, Letter _letter)
    {
        System.out.println(_position+"position");
        letter = _letter;
        position = _position;
        stampcount = cache.getStampcount()[position];
        for(int i = 0;i<stampcount.length;i++){
            System.out.print(stampcount[i]);
        }
        stamps = cache.getAllStamp().getPerStamps().get(position).getStamps();
    }

    public More_StampAdapter(int _position, Letter _letter,int year,int month,int day)
    {
        this(_position,_letter);
        this.year = year;
        this.month = month;
        this.day = day;
    }
    @NonNull
    @Override
    public More_StampAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stamp_item,viewGroup,false);
        final More_StampAdapter.ViewHolder holder = new More_StampAdapter.ViewHolder(view);

        holder.stampView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = holder.getAdapterPosition();
                letter.setStampviewid(letter.getStampviewid()/100*100+i+1);
                if(stampcount[i]<=0){
                    Toast.makeText(mContext,"邮票数量不够",Toast.LENGTH_SHORT).show();
                }else {
                    showNormalDialog();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull More_StampAdapter.ViewHolder viewHolder, int i) {
        System.out.println(position+"-"+i);
        //屏幕的宽度(px值）
        int screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        //Item的宽度，或图片的宽度
        int width = screenWidth/2;
        Glide.with(mContext).load(GetResouce.getResource(position+1,i+1)).override(width,SIZE_ORIGINAL).into(viewHolder.stampImage);
        viewHolder.stampText.setText(stampcount[i]+"");
    }

    @Override
    public int getItemCount() {
        return stamps.size()-1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View stampView;
        ImageView stampImage;
        TextView stampText;

        public ViewHolder(View view) {
            super(view);
            stampView = view;
            stampImage = (ImageView) view.findViewById(R.id.stamp_iamge);
            stampText = (TextView) view.findViewById(R.id.stamp_text);
        }
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
      //  normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("发送信件");
        normalDialog.setMessage("是否发送？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        JsonObject jsonuser = new JsonObject();
                        jsonuser.addProperty("receiver",letter.getReceiver());
                        jsonuser.addProperty("receiverid",letter.getReceiverid());
                        jsonuser.addProperty("sender",letter.getSender());
                        jsonuser.addProperty("senderid",letter.getSenderid());

                        jsonuser.addProperty("stampviewid",letter.getStampviewid());
                        jsonuser.addProperty("text",letter.getText());
                        jsonuser.addProperty("timestamp",letter.getTimestamp());
                        jsonuser.addProperty("type",letter.getType());
                        System.out.println(year+month+day);
                        Log.i("lettertext",letter.getText());
                        if(letter.getType()==2){
                            System.out.println("发送给自己");
                            jsonuser.addProperty("year",year);
                            jsonuser.addProperty("month",month);
                            jsonuser.addProperty("day",day);
                        }
                        uploaddate(jsonuser);

                        //Toast.makeText(mContext,"发送",Toast.LENGTH_SHORT);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();

    }

    private void showUnNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        //  normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("发送失败");
        normalDialog.setMessage("邮票数量不够");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // 显示
        normalDialog.show();

    }
    private void uploaddate(JsonObject jsonuser){
        RetrofitManager.create(ApiService.class).writingletter(cache.getUser().getToken(),jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<Object>>(){
                    @Override
                    public void accept(@NonNull ResponseData<Object> rd) throws Exception {
                        Toast.makeText(mContext,rd.getMsg(),Toast.LENGTH_SHORT);
                        ActivityCollector.finishAllStampActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("writingletter",throwable.getMessage());
                    }
                });
    }
}