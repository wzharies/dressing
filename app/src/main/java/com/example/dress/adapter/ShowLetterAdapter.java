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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dress.R;
import com.example.dress.activity.Museum_Activity;
import com.example.dress.util.GetResouce;

import com.example.dress.util.ShowLetter.ShowLetterGroup;
import com.example.dress.util.ShowLetter.TempShowLetter;
import com.example.dress.util.cache;

import java.util.ArrayList;
import java.util.List;

public class ShowLetterAdapter extends RecyclerView.Adapter<ShowLetterAdapter.ViewHolder>{
    private List<TempShowLetter> showLetterList;
    private Context mContext;
    private TempShowLetter letter;

    public ShowLetterAdapter(List<TempShowLetter> showLetterGroups)
    {
        showLetterList=showLetterGroups;
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
                Toast.makeText(mContext,"you ttttttttt",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowLetterAdapter.ViewHolder viewHolder, int i) {
        TempShowLetter tempShowLetter = showLetterList.get(i);
        int tag = tempShowLetter.getTag();
        viewHolder.showLetterTitle.setText(tempShowLetter.getTitle());
        viewHolder.showLetterImage.setImageResource(tempShowLetter.getIamgeSourse());
        viewHolder.showLetterText.setText(tempShowLetter.getText());
        if(tag==1){
            viewHolder.showLetterTag.setText("#标签1#");
        }
        else if(tag==2){
            viewHolder.showLetterTag.setText("#标签2#");
        }
        else {
            viewHolder.showLetterTag.setText("#标签3#");
        }

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
        TextView showLetterTag;

        public ViewHolder(View view){
            super(view);
            showLetterView=view;
            showLetterImage = (ImageView)view.findViewById(R.id.show_letter_image);
            showLetterText = (TextView)view.findViewById(R.id.show_letter_text);
            showLetterTitle = (TextView)view.findViewById(R.id.show_letter_title);
            showLetterTag = (TextView)view.findViewById(R.id.show_letter_tag);
        }
    }
}
