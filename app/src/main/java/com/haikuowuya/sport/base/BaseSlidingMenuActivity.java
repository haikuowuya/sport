package com.haikuowuya.sport.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.haikuowuya.core.util.DensityUtils;
import com.haikuowuya.sport.R;
import com.haikuowuya.sport.fragment.MenuFragment;
import com.haikuowuya.sport.slidingmenu.SlidingMenu;

/**
 * 中间显示一个标题的Activity的基类，标题的名称为{@link BaseActivity#getActivityTitle()}
 */
public abstract class BaseSlidingMenuActivity extends BaseTitleActivity
{
    protected SlidingMenu mSlidingMenu;
    private MenuFragment mMenuFragment;

    @Override
    public void setContentView(int layoutResID)
    {
        super.setContentView(layoutResID);
        initSlidingMenu();
    }

    @Override
    public void afterOnCreate(Bundle savedInstanceState)
    {
        super.afterOnCreate(savedInstanceState);
        /****
         * 当应用使用侧滑时，禁用滑动结束activity
         */
        setSwipeBackEnable(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            mSlidingMenu.toggle(true);
        }
        return true;
    }

    public void toggle()
    {
        mSlidingMenu.toggle(true);
    }

    private void initSlidingMenu()
    {
        mSlidingMenu = new SlidingMenu(mActivity);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.setShadowWidth(DensityUtils.dpToPx(mActivity, 15.f));
        mSlidingMenu.setBehindOffset(DensityUtils.dpToPx(mActivity, 120.f));
        mSlidingMenu.setFadeDegree(0.65f);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//        mSlidingMenu.setBehindCanvasTransformer(
//                new SlidingMenu.CanvasTransformer()
//                {
//                    @Override
//                    public void transformCanvas(Canvas canvas, float percentOpen)
//                    {
//                        float scale = (float) (percentOpen * 0.25 + 0.75);
//                        canvas.scale(scale, scale, canvas.getWidth() / 2, canvas.getHeight() / 2);
//                        // canvas.scale(percentOpen, 1, 0, 0);
//                    }
//                });
        mSlidingMenu.setMenu(R.layout.layout_menu);
        // 设置隐藏在AboveMenu菜单后面的菜单
        mMenuFragment = MenuFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_container, mMenuFragment).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mMenuFragment.onActivityResult(requestCode, resultCode, data);
    }
}
