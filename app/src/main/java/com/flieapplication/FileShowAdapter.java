package com.flieapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class FileShowAdapter extends RecyclerView.Adapter<FileShowAdapter.ItemViewHolder> {
    private List<File> mData;
    private Context context;
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void chooseFile(File file);
    }

    public void setItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public FileShowAdapter(Context context, List<File> data) {
        this.context = context;
        if (data!=null) {
            mData = data;
        }else {
            mData=new ArrayList<>();
        }
    }
    public void updateData(List<File> data){
        if (data!=null) {
            mData = data;
        }else {
            mData=new ArrayList<>();
        }
        notifyDataSetChanged();
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.file_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position).getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.chooseFile(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RelativeLayout relativeLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.file_name);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_content);
        }

    }
}
