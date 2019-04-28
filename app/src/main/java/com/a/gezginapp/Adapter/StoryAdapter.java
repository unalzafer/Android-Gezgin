package com.a.gezginapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.gezginapp.Model.StoryModel;
import com.a.gezginapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    ArrayList<StoryModel> arrayList;

    public StoryAdapter(ArrayList<StoryModel> list) {
        arrayList=list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView ivStory;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            tvTitle=(TextView)itemView.findViewById(R.id.tvTitle);
            ivStory=(ImageView)itemView.findViewById(R.id.ivStory);
        }
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_story,viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tvTitle.setText(arrayList.get(i).getTitle());
        Glide.with(viewHolder.context).load(arrayList.get(i).getPhoto()).into(viewHolder.ivStory);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
