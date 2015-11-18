package com.haikuowuya.sport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.sport.R;
import com.haikuowuya.sport.adapter.appointment.AppointmentRecyclerAdapter;
import com.haikuowuya.sport.base.BaseFragment;
import com.haikuowuya.sport.model.GymItem;
import com.haikuowuya.sport.util.DataUtils;

import java.util.List;

import butterknife.Bind;

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

    @Bind(R.id.rv_recycler_view)
    RecyclerView mRecyclerView;
    private List<GymItem> mGymItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_appointment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mGymItems = DataUtils.genMockGymItems();
        mRecyclerView.setAdapter(new AppointmentRecyclerAdapter(mGymItems));
    }

    @Override
    public String getFragmentTitle()
    {
        return "预约";
    }
}

