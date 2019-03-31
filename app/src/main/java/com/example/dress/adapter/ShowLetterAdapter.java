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
import com.example.dress.activity.Museum_Activity;
import com.example.dress.util.GetResouce;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.ShowLetter.ShowLetterGroup;
import com.example.dress.util.cache;

import java.util.ArrayList;
import java.util.List;

public class ShowLetterAdapter extends RecyclerView.Adapter<ShowLetterAdapter.ViewHolder>{
    private int[][] showLettercount;
    private List<ShowLetterGroup> showLetterList;
    private Context mContext;
    private ArrayList<Integer> whichhave;
    private Letter letter;

    public ShowLetterAdapter(List<ShowLetterGroup> showLetterGroups, int tag)
    {
        showLetterList=showLetterGroups;
        whichhave = cache.getWhichhava();
    }
    @NonNull
    @Override
    public ShowLetterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_letter_item,viewGroup,false);
        final ShowLetterAdapter.ViewHolder holder = new ShowLetterAdapter.ViewHolder(view);


        holder.showLetterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowLetterAdapter.ViewHolder viewHolder, int i) {
        ShowLetterGroup showLetters = showLetterList.get(whichhave.get(i));
        int cnt=0;
        for(int k = 0;k<showLettercount[whichhave.get(i)].length;k++){
            if(showLettercount[i][k]!=0)
                cnt++;
        }
        String count =cnt +"/"+showLettercount[i].length;
        viewHolder.showLetterText.setText(count);
    }

    @Override
    public int getItemCount() {
        return showLetterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View showLetterView;
        ImageView showLetterImage;
        TextView showLetterTitle;
        TextView showLetterText;

        public ViewHolder(View view){
            super(view);
            showLetterView=view;
            showLetterImage = (ImageView)view.findViewById(R.id.show_letter_image);
            showLetterText = (TextView)view.findViewById(R.id.show_letter_text);
            showLetterTitle = (TextView)view.findViewById(R.id.show_letter_title);
        }
    }
}
