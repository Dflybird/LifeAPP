package com.example.gq.lifeapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.model.bean.NoteBean;
import com.example.gq.lifeapp.presenter.impl.NotePresenter;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/19.
 */
public class NoteRecycleAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private ArrayList<NoteBean> arrayList;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private OnItemDeleteClickListener onItemDeleteClickListener;

    public NoteRecycleAdapter(ArrayList<NoteBean> arrayList, Context context) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int position) {
        holder.keyword.setText(arrayList.get(position).getKeyword());
        holder.time.setText(arrayList.get(position).getTime());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemDeleteClickListener != null){
                    onItemDeleteClickListener.onDeleteClickListener(holder.delete, position);
                    notifyItemRemoved(position);
                    Log.d("TAG", String.valueOf(position));
                    arrayList.remove(position);
                    notifyItemChanged(position);
                }
            }
        });
        holder.revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onClickListener(holder.revise, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public void setOnDeleteItemClickListener(OnItemDeleteClickListener listener){
        this.onItemDeleteClickListener = listener;
    }
}

class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView keyword, time;
    RelativeLayout delete, revise;

    public NoteViewHolder(View itemView) {
        super(itemView);
        keyword = (TextView) itemView.findViewById(R.id.tv_keyword_note);
        time = (TextView) itemView.findViewById(R.id.tv_time_note);
        delete = (RelativeLayout) itemView.findViewById(R.id.delete_button);
        revise = (RelativeLayout) itemView.findViewById(R.id.revise_button);
    }
}

interface OnItemClickListener{
    void onClickListener(View view, int position);
}

interface OnItemDeleteClickListener{
    void onDeleteClickListener(View view, int position);
}
