package com.example.gq.lifeapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.BaseActivity;
import com.example.gq.lifeapp.model.bean.NoteBean;
import com.example.gq.lifeapp.presenter.impl.NotePresenter;
import com.example.gq.lifeapp.view.intf.NoteViewIntf;

/**
 * Created by gq on 2015/8/20.
 */
public class WriteNoteActivity extends BaseActivity implements NoteViewIntf {

    private Toolbar toolbar;
    private EditText etTime, etKeyword, etContent;
    private NotePresenter presenter;
    private int id, size;

    public static void actionStart(Context context, int id, int size){
        Intent intent = new Intent(context, WriteNoteActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("size", size);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_note_write);

        findView();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        size = intent.getIntExtra("size", -1);

        presenter = new NotePresenter();
        presenter.showNote(id, this);
        toolbar.setTitle("便签");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.addNote(this);
    }

    private void back() {
        String keyword = etKeyword.getText().toString();
        String time = etTime.getText().toString();
        if (!keyword.equals("")){
            if (!time.equals("")){
                finish();
            } else Toast.makeText(this, "请填写时间", Toast.LENGTH_SHORT).show();
        } else finish();

    }

    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        etTime = (EditText) findViewById(R.id.note_time);
        etKeyword = (EditText) findViewById(R.id.note_keyword);
        etContent = (EditText) findViewById(R.id.note_content);
    }

    @Override
    public void setNoteInfo(NoteBean noteBean) {
        etTime.setText(noteBean.getTime());
        etKeyword.setText(noteBean.getKeyword());
        etContent.setText(noteBean.getContent());
    }

    @Override
    public NoteBean getNoteInfo() {
        NoteBean noteBean = new NoteBean();
        if (id == -1){
            noteBean.setId(size+1);
        } else noteBean.setId(id);
        noteBean.setTime(etTime.getText().toString());
        noteBean.setKeyword(etKeyword.getText().toString());
        noteBean.setContent(etContent.getText().toString());
        return noteBean;
    }

}
