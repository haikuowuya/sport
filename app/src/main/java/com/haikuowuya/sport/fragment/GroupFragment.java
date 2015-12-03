package com.haikuowuya.sport.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.sport.R;
import com.haikuowuya.sport.base.BaseFragment;
import com.haikuowuya.sport.view.TabViewPagerIndicator;

import butterknife.Bind;

/**
 * Created by Steven on 2015/12/2 0002.
 */
public class GroupFragment extends BaseFragment
{
    public static GroupFragment newInstance()
    {
        GroupFragment fragment = new GroupFragment();
        return fragment;
    }

    @Bind(R.id.tabview_viewpager)
    TabViewPagerIndicator mTabViewPagerIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mTabViewPagerIndicator.getIndicator().setVisibility(View.GONE);
        mTabViewPagerIndicator.setViewPagerAdapter(genMockPageAdapter());
    }

    private PagerAdapter genMockPageAdapter()
    {
        final BaseFragment[] baseFragments = {SubGroupFragment.newInstance(), AttentionFragment.newInstance()};
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return baseFragments[position];
            }

            @Override
            public int getCount()
            {
                return baseFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                return baseFragments[position].getFragmentTitle();
            }
        };
        return fragmentPagerAdapter;

    }

    @Override
    public String getFragmentTitle()
    {
        return null;
    }
}
