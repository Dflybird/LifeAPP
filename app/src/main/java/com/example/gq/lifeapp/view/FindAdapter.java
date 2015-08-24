package com.example.gq.lifeapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gq.lifeapp.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by gq on 2015/8/24.
 */
public class FindAdapter extends RecyclerView.Adapter<FindHolder> {

    private String[] item;
    private int[] itemIcon;
    private LayoutInflater inflater;
    private OnMapItemClickListener listener;

    public FindAdapter(String[] item, int[] itemIcon, Context context) {
        this.item = item;
        this.itemIcon = itemIcon;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public FindHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_find, parent, false);
        return new FindHolder(view);
    }

    @Override
    public void onBindViewHolder(final FindHolder holder,final int position) {
        holder.textView.setText(item[position]);
        holder.imageView.setImageResource(itemIcon[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClickListener(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.length;
    }

    public void setOnItemClickListener(OnMapItemClickListener listener){
        this.listener = listener;
    }

}
class FindHolder extends RecyclerView.ViewHolder{

    TextView textView;
    ImageView imageView;

    public FindHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_item_map);
        imageView = (ImageView) itemView.findViewById(R.id.iv_item_map);
    }
}

interface OnMapItemClickListener{
    void onClickListener(View view, int position);
}