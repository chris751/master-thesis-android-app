package com.thegoodthebadtheasian.myapplication.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thegoodthebadtheasian.myapplication.R;
import com.thegoodthebadtheasian.myapplication.models.Device;
import com.thegoodthebadtheasian.myapplication.models.PlaceholderDevice;
import com.thegoodthebadtheasian.myapplication.models.Sensor;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Device> mDataset;
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

    public MyAdapter(List<Device> myDataset, OnItemClickListener onItemClickListener) {
        mDataset = myDataset;

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
        Device thisDevice = mDataset.get(position);
        Sensor thisSensor = thisDevice.getTrigger().getSensors().get(0);
        holder.nameView.setText(thisSensor.getType());
        holder.priceView.setText(thisDevice.getAction().get_id());
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
