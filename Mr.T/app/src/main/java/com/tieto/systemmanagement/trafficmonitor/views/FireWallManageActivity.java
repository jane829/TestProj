package com.tieto.systemmanagement.trafficmonitor.views;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.tieto.systemmanagement.R;
import com.tieto.systemmanagement.trafficmonitor.adapter.MyViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by jane on 15-3-25.
 */
public class FireWallManageActivity  extends FragmentActivity implements ViewPager.OnPageChangeListener{
    private ArrayList<Fragment> fragments;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private TextView realtimeNetSpeed,monthlyTrafficStastic;
    private ImageView cursor;

    private int currentIndex = 0;
    private long offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_activity_firewall_manage_layout);
        cursor = (ImageView) findViewById(R.id.activity_firewall_cursor);
        viewPager = (ViewPager) findViewById(R.id.activity_firewall_viewpager);
        realtimeNetSpeed = (TextView) findViewById(R.id.activity_firewall_realtime);
        monthlyTrafficStastic = (TextView) findViewById(R.id.activity_firewall_monthly);
        realtimeNetSpeed.setOnClickListener(new MyOnClickListener(0));
        monthlyTrafficStastic.setOnClickListener(new MyOnClickListener(1));

        offset = cursor.getLayoutParams().width;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (int) (screenW / 2.0 - offset);

        fragments = new ArrayList<Fragment>();
        fragments.add(new RtNetSpeedFragement());
        fragments.add(new MonTrafficStatFragement());
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentIndex);
        viewPager.setOnPageChangeListener(this);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation anim = null;
        switch (position) {
            case 0:
                if(currentIndex ==1) {
                    anim =  new TranslateAnimation(offset,0,0,0);
                }
                break;
            case 1:
                if(currentIndex==0) {
                    anim = new TranslateAnimation(0,offset,0,0);
                }
                break;

        }
        currentIndex = position;
        anim.setDuration(500);
        anim.setFillAfter(true);
        anim.setRepeatCount(0);
        anim.setRepeatMode(Animation.REVERSE);
        cursor.startAnimation(anim);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;
        private MyOnClickListener(int i) {
            this.index = i;
        }

        @Override
        public void onClick(View view) {
            viewPager.setCurrentItem(index);
        }
    }
}
