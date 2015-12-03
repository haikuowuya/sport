package com.haikuowuya.sport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;

import com.haikuowuya.sport.R;
import com.haikuowuya.sport.base.BaseFragment;

/**
 * Created by Steven on 2015/11/3 0003.
 */
public class MainFragment extends BaseFragment
{
    public static MainFragment newInstance()
    {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    FragmentTabHost mFragmentTabHost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, null);
        initView(view);

        return view;
    }

    private void initView(View view)
    {
        mFragmentTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        setUpFragmentTabHost();
    }

    private void setUpFragmentTabHost()
    {
        mFragmentTabHost.setup(mActivity, mActivity.getSupportFragmentManager(), R.id.frame_real_tab_container);
        final Button btnAppointment = (Button) LayoutInflater.from(mActivity).inflate(R.layout.layout_home_tab_item, null);
        btnAppointment.setText("预约");
        btnAppointment.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.activated_appointment_selector, 0, 0);
        btnAppointment.setActivated(true);
        final Button btnGroup = (Button) LayoutInflater.from(mActivity).inflate(R.layout.layout_home_tab_item, null);
        btnGroup.setText("圈子");
        btnGroup.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.activated_group_selector, 0, 0);
        final Button btnSport = (Button) LayoutInflater.from(mActivity).inflate(R.layout.layout_home_tab_item, null);
        btnSport.setText("健身");
        btnSport.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.activated_sport_selector, 0, 0);
        MenuFragment menuFragment = MenuFragment.newInstance();
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(btnAppointment.getText().toString()).setIndicator(btnAppointment), AppointmentFragment.class, null);
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(btnGroup.getText().toString()).setIndicator(btnGroup), GroupFragment.class, null);
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(btnSport.getText().toString()).setIndicator(btnSport), menuFragment.getClass(), null);
        mActivity.setCenterTitle(btnAppointment.getText());
        mFragmentTabHost.setOnTabChangedListener(
                new TabHost.OnTabChangeListener()
                {
                    public void onTabChanged(String tabId)
                    {
                        //  String currentTabTag = mFragmentTabHost.getCurrentTabTag();
                        //  System.out.println("currentTabTag = " + currentTabTag + " tabId = " + tabId);
                        btnAppointment.setActivated(tabId.equals(btnAppointment.getText()));
                        btnGroup.setActivated(tabId.equals(btnGroup.getText()));
                        btnSport.setActivated(tabId.equals(btnSport.getText()));
                        mActivity.setCenterTitle(tabId);
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public String getFragmentTitle()
    {
        return "首页";
    }

}
