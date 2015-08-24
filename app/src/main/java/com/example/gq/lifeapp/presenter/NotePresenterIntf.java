package com.example.gq.lifeapp.presenter;

import com.example.gq.lifeapp.model.bean.NoteBean;
import com.example.gq.lifeapp.view.intf.NoteViewIntf;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/20.
 */
public interface NotePresenterIntf {

    void addNote(NoteViewIntf noteView);

    void deleteNote(int id);

    void showNote(int id, NoteViewIntf noteView);

    ArrayList<NoteBean> getAll();
}
