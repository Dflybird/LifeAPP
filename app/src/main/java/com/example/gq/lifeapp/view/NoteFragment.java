package com.example.gq.lifeapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.BaseFrgment;
import com.example.gq.lifeapp.app.MyApplication;
import com.example.gq.lifeapp.model.bean.NoteBean;
import com.example.gq.lifeapp.presenter.impl.NotePresenter;
import com.example.gq.lifeapp.util.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/12.
 */
public class NoteFragment extends BaseFrgment {

    public static final int SUCCESS = 1;

    private RecyclerView recyclerView;
    private Button addButton;
    private ArrayList<NoteBean> mArrayList;
    private NotePresenter mNotePresenter;
    private NoteRecycleAdapter adapter;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view);

        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteNoteActivity.actionStart(getActivity(), -1, mArrayList.size());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mNotePresenter = new NotePresenter();
                mArrayList = mNotePresenter.getAll();
                adapter = new NoteRecycleAdapter(mArrayList, MyApplication.getContext());

                Message message = new Message();
                message.what = SUCCESS;
                message.obj = adapter;
                handler.sendMessage(message);
            }
        }).start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS:
                        adapter = (NoteRecycleAdapter) msg.obj;
                        adapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onClickListener(View view, int position) {
                                WriteNoteActivity.actionStart(getActivity(), position + 1, mArrayList.size());
                            }
                        });
                        adapter.setOnDeleteItemClickListener(new OnItemDeleteClickListener() {
                            @Override
                            public void onDeleteClickListener(View view, int position) {
                                mNotePresenter.deleteNote(position + 1);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void findView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_note);
        addButton = (Button) view.findViewById(R.id.add_note);
    }
}
