package com.haikuowuya.sport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.sport.R;
import com.haikuowuya.sport.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.Bind;

/**
 * Created by Steven on 2015/12/2 0002.
 */
public class SubGroupFragment extends BaseFragment
{
    public static SubGroupFragment newInstance()
    {
        SubGroupFragment fragment = new SubGroupFragment();
        return fragment;
    }

    @Bind(R.id.rv_recycler_view)
    XRecyclerView mXRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sub_group, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public String getFragmentTitle()
    {
        return "圈组";
    }
}
