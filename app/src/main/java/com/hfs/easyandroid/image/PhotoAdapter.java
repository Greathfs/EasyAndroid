package com.hfs.easyandroid.image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hfs.easyandroid.R;
import com.hfs.easyimageloader.EasyImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuzhao on 2018/3/3.
 */

public class PhotoAdapter extends RecyclerView.Adapter {
    private List<PhotoBean> curList = new ArrayList<>();
    private Context context;
    public void setList(List<PhotoBean> list){
        if (list == null) {
            return;
        }
        curList=list;
    }
    public PhotoAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoHolder(LayoutInflater.from(context).inflate(R.layout.item_photo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PhotoBean bean = curList.get(position);
        if (holder instanceof PhotoHolder) {
            EasyImageLoader.getInstance()
                    .load(bean.url)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((PhotoHolder) holder).imageView);

        }
    }

    @Override
    public int getItemCount() {
        return curList.size();
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public PhotoHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.photo_id);
        }

    }
}
