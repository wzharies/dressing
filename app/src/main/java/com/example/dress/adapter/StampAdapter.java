package com.example.dress.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dress.R;
import com.example.dress.activity.StampViewActivity;
import com.example.dress.util.Stamp;


import java.util.List;

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewHolder> {

    private List<Stamp> stampList;
    private Context mContext;

    public StampAdapter(List<Stamp> stamps)
    {
        stampList=stamps;
    }
    @NonNull
    @Override
    public StampAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stamp_item,viewGroup,false);
        final StampAdapter.ViewHolder holder = new StampAdapter.ViewHolder(view);
        holder.stampImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Stamp stampTemp = stampList.get(position);
                Intent intent = new Intent(mContext, StampViewActivity.class);
                intent.putExtra("group_index",stampTemp.getGroupIndex());
                mContext.startActivity(intent);
            }
        });
        holder.stampText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
}
