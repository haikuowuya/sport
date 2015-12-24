package com.haikuowuya.sport;

import android.content.Intent;
import android.os.Bundle;

import com.haikuowuya.core.base.BaseHKWYFragment;
import com.haikuowuya.core.base.BaseHKWYSlidingMenuActivity;
import com.haikuowuya.core.slidingmenu.SlidingMenu;
import com.haikuowuya.sport.fragment.MainFragment;
import com.haikuowuya.sport.fragment.MenuFragment;

public class MainActivity extends BaseHKWYSlidingMenuActivity
{

    private MenuFragment mMenuFragment;

    @Override
    public BaseHKWYFragment fragmentAsView()
    {
        return MainFragment.newInstance();
    }

    @Override
    public CharSequence getActivityTitle()
    {
        return "首页";
    }

    @Override
    public BaseHKWYFragment fragmentAsMenu()
    {
        mMenuFragment = MenuFragment.newInstance();
        return mMenuFragment;
    }

    @Override
    public void afterOnCreate(Bundle savedInstanceState)
    {
        super.afterOnCreate(savedInstanceState);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mMenuFragment)
        {
            mMenuFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
