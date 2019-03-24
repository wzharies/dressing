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
import com.example.dress.util.GetResouce;
import com.example.dress.util.Stamp.AllStamp;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.Stamp.Stamp;


import java.io.Serializable;
import java.util.List;

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewHolder> {

    private List<PerStamp> stampList;
    private Context mContext;

    public StampAdapter(List<PerStamp> stamps)
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


        holder.stampView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                PerStamp stampTemp = stampList.get(position);
                Intent intent = new Intent(mContext, StampViewActivity.class);
                intent.putExtra("group_index",stampTemp);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StampAdapter.ViewHolder viewHolder, int i) {
        PerStamp stamp = stampList.get(i);
        String count = stamp.getHave()+"/"+stamp.getCount();
        viewHolder.stampImage.setImageResource(GetResouce.getResource(stamp.getOrder(),0));
        viewHolder.stampText.setText(count);
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
