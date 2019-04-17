package com.thegoodthebadtheasian.myapplication;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thegoodthebadtheasian.myapplication.models.PlaceholderDevice;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<PlaceholderDevice> mDataset;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameView;
        public TextView priceView;
        public ConstraintLayout parentLayout;

        OnItemClickListener onItemClickListener;

        public MyViewHolder(View v, OnItemClickListener onItemClickListener){
            super(v);
            nameView = itemView.findViewById(R.id.name_view);
            priceView = itemView.findViewById(R.id.priceView);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            this.onItemClickListener = onItemClickListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public MyAdapter(Context context ,List<PlaceholderDevice> myDataset, OnItemClickListener onItemClickListener) {
        mDataset = myDataset;
        this.mContext = context;

        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_listitem, parent, false);

        MyViewHolder vh = new MyViewHolder(view, mOnItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        PlaceholderDevice thisDevice = mDataset.get(position);
        holder.nameView.setText(thisDevice.getName());
        holder.priceView.setText(thisDevice.getPrice() + "");
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
