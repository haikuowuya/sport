package com.haikuowuya.sport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.sport.R;
import com.haikuowuya.sport.base.BaseFragment;

/**
 * 预约Fragment界面
 */
public class AppointmentFragment extends BaseFragment
{
    public static AppointmentFragment newInstance()
    {
        AppointmentFragment fragment = new AppointmentFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_appointment, null);
        initView(view);
        return view;
    }

    private void initView(View view)
    {

    }

    @Override
    public String getFragmentTitle()
    {
        return "预约";
    }
}
