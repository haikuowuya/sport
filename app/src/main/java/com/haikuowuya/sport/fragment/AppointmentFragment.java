package com.haikuowuya.sport.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haikuowuya.core.util.DensityUtils;
import com.haikuowuya.sport.R;
import com.haikuowuya.sport.adapter.home.appointment.AppointmentRecyclerAdapter;
import com.haikuowuya.sport.base.BaseFragment;
import com.haikuowuya.sport.model.GymItem;
import com.haikuowuya.sport.util.DataUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

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
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.tv_select)
    TextView mTvSelect;
    @Bind(R.id.tv_zhineng)
    TextView mTvZhiNeng;

    private List<GymItem> mGymItems;
    private PopupWindow mPopupWindow;
    View mPopupView;
    private int mCurrentType = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_appointment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mGymItems = DataUtils.genMockGymItems();
        mRecyclerView.setAdapter(new AppointmentRecyclerAdapter(mGymItems));
        initListPopupWindow();
        initPopupWindow();

    }

    private void initPopupWindow()
    {
        mPopupWindow = new PopupWindow(mActivity);
        // 设置SelectPicPopupWindow弹出窗体的宽
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        mPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        mPopupWindow.setFocusable(true);
        //设置点击窗口外边窗口消失
        mPopupWindow.setOutsideTouchable(false);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        mPopupWindow.setAnimationStyle(R.style.ListPopupWindowStyle);
        // 设置SelectPicPopupWindow弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupView = LayoutInflater.from(mActivity).inflate(R.layout.layout_popup_view, null);
        ListView listView = (ListView) mPopupView.findViewById(R.id.lv_listview);
        int listViewHeight = DensityUtils.getScreenHeightInPx(mActivity)*3/5;
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,listViewHeight));
        ListAdapter adapter = ArrayAdapter.createFromResource(mActivity, R.array.array_city_array, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListenerImpl());
        mPopupView.findViewById(R.id.view_view).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (null != mPopupWindow && mPopupWindow.isShowing())
                {
                    mPopupWindow.dismiss();
                }
            }
        });
        mPopupWindow.setContentView(mPopupView);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            public void onDismiss()
            {
                mTvCity.setActivated(false);
                mTvSelect.setActivated(false);
                mTvZhiNeng.setActivated(false);
            }
        });
    }

    private void initListPopupWindow()
    {
        ListPopupWindow mListPopupWindow = new ListPopupWindow(mActivity);
        mListPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mListPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mListPopupWindow.setForceIgnoreOutsideTouch(true);
        mListPopupWindow.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        mListPopupWindow.setHeight(DensityUtils.dpToPx(mActivity, 300.f));
        ListAdapter adapter = ArrayAdapter.createFromResource(mActivity, R.array.array_city_array, android.R.layout.simple_list_item_1);
        mListPopupWindow.setAdapter(adapter);
        mListPopupWindow.setAnimationStyle(R.style.ListPopupWindowStyle);
    }

    @OnClick(value = {R.id.frame_city, R.id.tv_city})
    void onCityClick(View view)
    {
        int offsetX = (int) ((DensityUtils.getScreenWidthInPx(mActivity) / 3 - StaticLayout.getDesiredWidth(mTvCity.getText(), mTvCity.getPaint())) / 2);
        mPopupWindow.showAsDropDown(view, 0 - offsetX, 0);
        mCurrentType = 0;
        mTvCity.setActivated(true);
    }

    @OnClick(value = {R.id.frame_zhineng, R.id.tv_zhineng})
    void onZhiNengClick(View view)
    {
        mCurrentType = 1;
        int offsetX = (int) (DensityUtils.getScreenWidthInPx(mActivity) / 3 + ((DensityUtils.getScreenWidthInPx(mActivity) / 3 - StaticLayout.getDesiredWidth(mTvZhiNeng.getText(), mTvZhiNeng.getPaint())) / 2));
        mPopupWindow.showAsDropDown(view, 0 - offsetX, 0);
        mTvZhiNeng.setActivated(true);
    }

    @OnClick(value = {R.id.frame_select, R.id.tv_select})
    void onSelectClcik(View view)
    {
        mCurrentType = 2;
        int offsetX = (int) (DensityUtils.getScreenWidthInPx(mActivity) * 2 / 3 + ((DensityUtils.getScreenWidthInPx(mActivity) / 3 - StaticLayout.getDesiredWidth(mTvSelect.getText(), mTvSelect.getPaint())) / 2));
        mPopupWindow.showAsDropDown(view, 0 - offsetX, 0);
        mTvSelect.setActivated(true);
    }

    @Override
    public String getFragmentTitle()
    {
        return "预约";
    }

    private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            if (null != mPopupWindow && mPopupWindow.isShowing())
            {
                mPopupWindow.dismiss();
                if (mCurrentType == 0)
                {
                    mTvCity.setText(parent.getAdapter().getItem(position).toString());
                } else if (mCurrentType == 1)
                {
                    mTvZhiNeng.setText(parent.getAdapter().getItem(position).toString());

                } else if (mCurrentType == 2)
                {
                    mTvSelect.setText(parent.getAdapter().getItem(position).toString());
                }
            }
        }
    }
}

