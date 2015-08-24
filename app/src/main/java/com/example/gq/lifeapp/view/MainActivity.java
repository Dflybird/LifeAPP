package com.example.gq.lifeapp.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.BaseActivity;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private MyFragmentAdapter mFragmentAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ResideMenu mResideMenu;
    private String[] menuItem;
    private int[] menuIcon;
    private ResideMenuItem calendar, about, setting, exit;
    CustomViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mResideMenu.isOpened()) {
                    mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                } else {
                    mResideMenu.closeMenu();
                }
            }
        });

        mViewPager = (CustomViewPager) findViewById(R.id.viewpager);
        mViewPager.setPagingEnabled(false);
        PagerSlidingTabStrip mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        initData();
        mViewPager.setAdapter(mFragmentAdapter);
        mTabStrip.setViewPager(mViewPager);

        addResideMenu();
    }

    private void initData() {
        fragments.add(new NoteFragment());
        fragments.add(new WeatherFragment());
        fragments.add(new FindFragment());

        titles.add("便签");
        titles.add("天气");
        titles.add("发现");

        mFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),
                fragments, titles);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    private void addResideMenu() {
        mResideMenu = new ResideMenu(this);
        mResideMenu.setBackground(R.mipmap.background_menu);
        mResideMenu.attachToActivity(this);
        mResideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        mResideMenu.addIgnoredView(mViewPager);

        calendar = new ResideMenuItem(this, R.mipmap.calendar_white, "日历");
        about = new ResideMenuItem(this, R.mipmap.about_white, "关于");
        setting = new ResideMenuItem(this, R.mipmap.setting_white, "设置");
        exit = new ResideMenuItem(this, R.mipmap.exit_white, "退出");

        mResideMenu.addMenuItem(calendar, ResideMenu.DIRECTION_LEFT);
        mResideMenu.addMenuItem(about, ResideMenu.DIRECTION_LEFT);
        mResideMenu.addMenuItem(setting, ResideMenu.DIRECTION_LEFT);
        mResideMenu.addMenuItem(exit, ResideMenu.DIRECTION_LEFT);

        calendar.setOnClickListener(this);
        about.setOnClickListener(this);
        setting.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mResideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        if (v == calendar) {
            try {
                Intent i = new Intent();
                ComponentName cn = null;
                if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
                    cn = new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");
                } else {
                    cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");
                }
                i.setComponent(cn);
                startActivity(i);
            } catch (ActivityNotFoundException e){
                e.printStackTrace();
            }
        } else if (v == about) {
            AboutUsActivity.startActivity(this);
        } else if (v == setting) {
            Toast.makeText(this, R.string.wait, Toast.LENGTH_SHORT).show();

        } else if (v == exit) {
            finish();
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
        }
        return false;
    }
}
