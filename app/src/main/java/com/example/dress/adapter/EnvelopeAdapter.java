package com.example.dress.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dress.R;
import com.example.dress.activity.EnvelopeViewActivity;
import com.example.dress.activity.More_letterActivity;
import com.example.dress.util.GetResouce;
import com.example.dress.util.Letter.Letter;

import java.lang.reflect.Field;
import java.util.List;

public class EnvelopeAdapter extends RecyclerView.Adapter<EnvelopeAdapter.ViewHolder> {
    private List<Letter> envelopeList;
    private Context mContext;

    public EnvelopeAdapter(List<Letter> envelopes)
    {
        envelopeList=envelopes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.envelope_item,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Letter envetemp = envelopeList.get(position);
                Intent intent = new Intent(mContext, EnvelopeViewActivity.class);
                intent.putExtra("envelope_data",envetemp);
                mContext.startActivity(intent);
            }
        });
        holder.sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, More_letterActivity.class);
                intent.putExtra("position",holder.getAdapterPosition());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Letter envelope = envelopeList.get(i);
        viewHolder.text.setText(envelope.getText());
        viewHolder.sender.setText(envelope.getSender());
        viewHolder.receiver.setText("亲爱的"+envelope.getReceiver()+":");
        int group = envelope.getStampviewid()/100;
        int index = envelope.getStampviewid()%100;
        viewHolder.stampImage.setImageResource(GetResouce.getResource(group,index));
    }

    @Override
    public int getItemCount() {
        return envelopeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View envelopeView;
        ImageView stampImage;
        TextView text;
        TextView sender;
        TextView receiver;
        public ViewHolder(View view){
            super(view);
            envelopeView=view;
            stampImage = (ImageView)view.findViewById(R.id.envelope_stamp_image);
            text = (TextView)view.findViewById(R.id.envelope_text);
            sender = (TextView)view.findViewById(R.id.envelope_sender);
            receiver=(TextView)view.findViewById(R.id.receiver_text);
        }
    }

    //  添加数据
    public void addData(int position,Letter envelope) {
//      在list中添加数据，并通知条目加入一条
        envelopeList.add(position, envelope);
        //添加动画
        notifyItemInserted(position);
    }
    //  删除数据
    public void removeData(int position) {
        envelopeList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void changeDate(List<Letter> envelopeList){
        this.envelopeList = envelopeList;
        notifyDataSetChanged();
    }

}
