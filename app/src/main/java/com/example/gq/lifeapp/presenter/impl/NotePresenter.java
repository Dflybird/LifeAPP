package com.example.gq.lifeapp.presenter.impl;

import android.util.Log;

import com.example.gq.lifeapp.model.bean.NoteBean;
import com.example.gq.lifeapp.model.impl.NoteModel;
import com.example.gq.lifeapp.presenter.NotePresenterIntf;
import com.example.gq.lifeapp.view.intf.NoteViewIntf;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/20.
 */
public class NotePresenter implements NotePresenterIntf {

    private NoteModel noteModel = NoteModel.getInstance();
    private ArrayList<NoteBean> arrayList;
    private NoteBean noteBean;


    @Override
    public void addNote(NoteViewIntf noteView) {
        noteBean = noteView.getNoteInfo();
        String keyword = noteBean.getKeyword();
        String time = noteBean.getTime();
        int maxID = noteModel.getMaxID();
        if (!keyword.equals("")){
            if (!time.equals("")){
                int id = noteBean.getId();
                if (id > maxID){
                    noteModel.add(noteBean);
                } else {
                    noteModel.change(id, noteBean);
                }
            }

        }

    }

    @Override
    public void deleteNote(int id) {
        noteModel.delete(id);
    }

    @Override
    public void showNote(int id, NoteViewIntf noteView) {
        if (id == -1){
            noteBean = new NoteBean();
            noteBean.setTime("");
            noteBean.setKeyword("");
            noteBean.setContent("");
        } else {
            noteBean = noteModel.query(id);
        }
        noteView.setNoteInfo(noteBean);
    }

    @Override
    public ArrayList<NoteBean> getAll() {
        arrayList = noteModel.queryAll();
        return arrayList;
    }
}
