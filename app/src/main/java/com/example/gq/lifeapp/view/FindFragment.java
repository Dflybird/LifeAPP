package com.example.gq.lifeapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.BaseFrgment;
import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.util.DividerItemDecoration;

/**
 * Created by gq on 2015/8/12.
 */
public class FindFragment extends BaseFrgment {

    private String[] item;
    private int[] itemIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView  recyclerView = (RecyclerView) view.findViewById(R.id.recycler_map);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        item = new String[]{"地图", "周边"};
        itemIcon = new int[]{R.mipmap.map_red, R.mipmap.around_green};

        FindAdapter adapter =  new FindAdapter(item, itemIcon, MyApplication.getContext());
        adapter.setOnItemClickListener(new OnMapItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                switch (position){
                    case 0:
                        MapActivity.startActivity(getActivity());
                        break;
                    case 1:
                        AroundActivity.startActivity(getActivity());
                        break;
                    default:
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
}

