package com.example.dress.adapter;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dress.R;


import java.util.List;


public class ShopStampsAdapter extends RecyclerView.Adapter<ShopStampsAdapter.ViewHolder> {

    private Context mContext;
    private List<int[]> mstampList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView count;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.shopstamps_imageview);
            count = (TextView) view.findViewById(R.id.shopstamps_textview);
        }
    }

    public ShopStampsAdapter(List<int[]> stampList){
        mstampList=stampList;
    }

    @NonNull
    @Override
    public ShopStampsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }
        View view=LayoutInflater.from(mContext).inflate(R.layout.shopstamps_item,viewGroup,false);
        final ShopStampsAdapter.ViewHolder holder=new ShopStampsAdapter.ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click",Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int i[]=mstampList.get(position);
            holder.count.setText("共 "+i[1]+"张图片");
        Glide.with(mContext).load(R.drawable.ic_info_black_24dp).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mstampList.size();
    }
}
