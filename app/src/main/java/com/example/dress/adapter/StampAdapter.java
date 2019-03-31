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

import com.bumptech.glide.Glide;
import com.example.dress.R;
import com.example.dress.activity.More_StampActivity;
import com.example.dress.activity.More_letterActivity;
import com.example.dress.activity.TempStampViewActivity;
import com.example.dress.util.GetResouce;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.cache;


import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewHolder> {
    private int[][] stampcount;
    private List<PerStamp> stampList;
    private Context mContext;
    private ArrayList<Integer> whichhave;
    private Letter letter;

    private int year;
    private int month;
    private int day;

    public StampAdapter(List<PerStamp> stamps, int[][] stampcounts, Letter _letter)
    {
        stampList=stamps;
        stampcount = stampcounts;
        whichhave = cache.getWhichhava();
        letter = _letter;
    }

    public StampAdapter(List<PerStamp> stamps, int[][] stampcounts, Letter _letter,int year,int month,int day)
    {
        this(stamps,stampcounts,_letter);
        this.year=year;
        this.month=month;
        this.day=day;
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
                Intent intent = new Intent(mContext, More_StampActivity.class);
                intent.putExtra("stamp",whichhave.get(position));
                letter.setStampviewid((whichhave.get(position)+1)*100);
                intent.putExtra("letter",letter);
                if(letter.getType()==2){
                    intent.putExtra("year",year);
                    intent.putExtra("month",month);
                    intent.putExtra("day",day);
                }
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StampAdapter.ViewHolder viewHolder, int i) {
        PerStamp stamp = stampList.get(whichhave.get(i));
        int cnt=0;
        for(int k = 0;k<stampcount[whichhave.get(i)].length;k++){
            if(stampcount[i][k]!=0)
                cnt++;
        }
        String count =cnt +"/"+stampcount[i].length;
        int screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        //Item的宽度，或图片的宽度
        int width = screenWidth/2;
        Glide.with(mContext).load(GetResouce.getResource(stamp.getId(),1)).override(width,SIZE_ORIGINAL).into(viewHolder.stampImage);
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
