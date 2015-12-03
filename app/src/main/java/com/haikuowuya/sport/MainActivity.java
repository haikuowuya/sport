package com.haikuowuya.sport;

import com.haikuowuya.sport.base.BaseFragment;
import com.haikuowuya.sport.base.BaseSlidingMenuActivity;
import com.haikuowuya.sport.fragment.MainFragment;

public class MainActivity extends BaseSlidingMenuActivity
{
    @Override
    public BaseFragment fragmentAsView()
    {
        return MainFragment.newInstance();
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return "首页";
    }
}
