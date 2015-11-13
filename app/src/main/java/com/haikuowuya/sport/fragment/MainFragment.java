package com.haikuowuya.sport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        final Button btn1 = new Button(mActivity);
        btn1.setTextColor(0xFFFFFFFF);
        btn1.setText("TAB 1");
        btn1.setTextSize(15.f);
        final Button btn2 = new Button(mActivity);
        btn2.setTextColor(0xFFFFFFFF);
        btn2.setText("TAB 2");
        btn2.setTextSize(15.f);
        final Button btn3 = new Button(mActivity);
        btn3.setTextColor(0xFFFFFFFF);
        btn3.setText("TAB 3");
        btn3.setTextSize(15.f);
        MenuFragment menuFragment = MenuFragment.newInstance();
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(btn1.getText().toString()).setIndicator(btn1), menuFragment.getClass(), null);
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(btn2.getText().toString()).setIndicator(btn2), menuFragment.getClass(), null);
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(btn3.getText().toString()).setIndicator(btn3), menuFragment.getClass(), null);

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
