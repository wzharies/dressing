package com.example.dress.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dress.R;
import com.example.dress.activity.StampViewActivity;
import com.example.dress.activity.WtringActivity;
import com.example.dress.util.Stamp;


import java.util.List;

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewHolder> {

    private List<Stamp> stampList;
    private Context mContext;
    int index_tag;                  //if tag==0;则是处于第一个界面选择邮票组别，
                                    //if tag==1;则处于第二个界面可以用选择确定邮票

    public class ViewHolder extends RecyclerView.ViewHolder{
        View stampView;
        ImageView stampImage;
        TextView stampText;

        public ViewHolder(View view){
            super(view);
            stampView=view;
            stampImage = (ImageView)view.findViewById(R.id.stamp_iamge);
            stampText = (TextView)view.findViewById(R.id.stamp_text);


        }
    }

    public StampAdapter(List<Stamp> stamps,int tag)
    {
        stampList=stamps;
        index_tag=tag;
    }
    @NonNull
    @Override
    public StampAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stamp_item,viewGroup,false);
        final StampAdapter.ViewHolder holder = new StampAdapter.ViewHolder(view);
        //第一层选择那一组的界面
        if(index_tag==0) {
            holder.stampImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Stamp stampTemp = stampList.get(position);
                    Intent intent = new Intent(mContext, StampViewActivity.class);
                    intent.putExtra("group_index", stampTemp.getGroupIndex());
                    mContext.startActivity(intent);
                }
            });
        }

        //这里是第二个层确定哪张邮票的界面
        else if(index_tag==1) {
            //先获取这个邮票信息

            holder.stampImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Stamp stampTemp = stampList.get(position);

                    if (stampTemp.getIsGet() == 0) {
                        AlertDialog show = new AlertDialog.Builder(mContext)
                                .setTitle("抱歉")
                                .setMessage("你还没有获取这张邮票")
                                .setPositiveButton("知道了", null)
                                .show();
                    }
                    else {
                        AlertDialog show = new AlertDialog.Builder(mContext)
                                .setTitle("确认你的选择")
                                .setMessage("确定选择这张邮票吗？")
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int position = holder.getAdapterPosition();
                                        Stamp stampTemp = stampList.get(position);
                                        Intent intent = new Intent(mContext, WtringActivity.class);
                                        intent.putExtra("group", stampTemp.getGroupIndex());
                                        intent.putExtra("index", stampTemp.getIndex());
                                        mContext.startActivity(intent);
                                    }
                                })
                                .setNegativeButton("再想想", null)
                                .show();
                    }
                }
            });
        }
        /*
        holder.stampText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StampAdapter.ViewHolder viewHolder, int i) {
        Stamp stamp = stampList.get(i);
        viewHolder.stampImage.setImageResource(stamp.getImageSourse());
        viewHolder.stampText.setText(stamp.getText());
       // viewHolder.text.setText(stamp.getText());
        //viewHolder.sender.setText(stamp.getSender());
        //viewHolder.receiver.setText("亲爱的"+stamp.getReceiver()+":");
    }

    @Override
    public int getItemCount() {
        return stampList.size();
    }

}
