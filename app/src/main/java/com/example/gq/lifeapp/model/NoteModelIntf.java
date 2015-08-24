package com.example.gq.lifeapp.model;

import com.example.gq.lifeapp.model.bean.NoteBean;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/19.
 */
public interface NoteModelIntf {

    void add(NoteBean noteBean);

    void delete(int id);

    void change(int id, NoteBean noteBean);

    NoteBean query(int id);

    ArrayList<NoteBean> queryAll();
}
