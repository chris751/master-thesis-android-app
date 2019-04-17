package com.thegoodthebadtheasian.myapplication;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thegoodthebadtheasian.myapplication.models.Action;

import java.util.List;

class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.ViewHolder>{
    private List<Action> mActions;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameView;
        public ConstraintLayout parentLayout;

        OnItemClickListener onItemClickListener;

        public ViewHolder(View v, OnItemClickListener onItemClickListener){
            super(v);
            nameView = itemView.findViewById(R.id.nameView);

            this.onItemClickListener = onItemClickListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public ActionListAdapter(Context context, List<Action> actions, OnItemClickListener onItemClickListener){
        this.mContext = context;
        mActions = actions;

        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ActionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_listitem, parent, false );

        ViewHolder vh = new ViewHolder(view, mOnItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Action action = mActions.get(position);
        holder.nameView.setText(action.getName());
    }

    @Override
    public int getItemCount() {
        return mActions.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
