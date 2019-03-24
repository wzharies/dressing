package com.example.dress.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dress.R;
import com.example.dress.activity.EnvelopeViewActivity;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.Letter.PerLetter;

public class PersonLetterAdapter extends RecyclerView.Adapter<PersonLetterAdapter.ViewHolder>{
    PerLetter perLetter;
    private Context mContext;

    public PersonLetterAdapter(PerLetter perLetter)
    {
        this.perLetter = perLetter;
    }
    @NonNull
    @Override
    public PersonLetterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myletter,viewGroup,false);
        final PersonLetterAdapter.ViewHolder holder = new PersonLetterAdapter.ViewHolder(view);
        holder.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Letter letter = perLetter.getPerletter().get(position);
                Intent intent = new Intent(mContext, EnvelopeViewActivity.class);
                intent.putExtra("envelope_data",letter);
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonLetterAdapter.ViewHolder viewHolder, int i) {
        if(perLetter.getPerletter().get(i).getReceiverid()==perLetter.getReceiveid()){
            Glide.with(mContext).load(R.drawable.sex1).into(viewHolder.left);
            viewHolder.leftline.setBackgroundResource(R.color.yellow);
            viewHolder.right.setVisibility(View.INVISIBLE);
            viewHolder.rightline.setVisibility(View.INVISIBLE);
        }else{
            Glide.with(mContext).load(R.drawable.sex2).into(viewHolder.right);
            viewHolder.rightline.setBackgroundResource(R.color.blue);
            viewHolder.left.setVisibility(View.INVISIBLE);
            viewHolder.left.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return perLetter.getPerletter().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View leftline;
        View rightline;
        ImageView left;
        ImageView right;
        public ViewHolder(View view){
            super(view);
            leftline = (View)view.findViewById(R.id.myletter_leftline);
            rightline = (View)view.findViewById(R.id.myletter_rightline);
            left = (ImageView)view.findViewById(R.id.myletter_leftimage);
            right = (ImageView)view.findViewById(R.id.myletter_rightimage);
        }
    }

    //  添加数据
    public void addData(int position,Letter envelope) {
//      在list中添加数据，并通知条目加入一条
        perLetter.getPerletter().add(position, envelope);
        //添加动画
        notifyItemInserted(position);
    }
    //  删除数据
    public void removeData(int position) {
        perLetter.getPerletter().remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
