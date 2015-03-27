package com.tieto.systemmanagement.trafficmonitor.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tieto.systemmanagement.R;
import com.tieto.systemmanagement.trafficmonitor.adapter.MyMonthTrafficInfoAdapter;
import com.tieto.systemmanagement.trafficmonitor.entity.MyAppInfo;
import com.tieto.systemmanagement.trafficmonitor.entity.MyTrafficStats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jane on 15-3-26.
 */
public class MonTrafficStatFragement extends Fragment {
    private ListView listView;
    private List<MyAppInfo> appInfos;
    private MyMonthTrafficInfoAdapter adapter;
    private MyTrafficStats trafficStats;

    public MonTrafficStatFragement() {
        super();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.firewall_listView);
        appInfos = new ArrayList<MyAppInfo>();
        trafficStats = new MyTrafficStats(this.getActivity());
        appInfos = trafficStats.getTrafficInfoForEachApp();
//        appInfos.add(new MyAppInfo("QQ",20,20,3,23,45));
//        appInfos.add(new MyAppInfo("WeChat",20,20,3,23,45));
        adapter = new MyMonthTrafficInfoAdapter(this.getActivity(),appInfos);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.t_firewall_fragement,container,false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "FriendFragment:onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
