package com.example.gq.lifeapp.view.intf;

import com.example.gq.lifeapp.model.bean.NoteBean;

import java.util.ArrayList;

/**
 * Created by gq on 2015/8/19.
 */
public interface NoteViewIntf {

    void setNoteInfo(NoteBean noteBean);

    NoteBean getNoteInfo();
}
