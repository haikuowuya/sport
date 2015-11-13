package com.haikuowuya.sport.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.haikuowuya.sport.R;
import com.haikuowuya.sport.adapter.menu.MenuRecyclerAdapter;
import com.haikuowuya.sport.base.BaseFragment;
import com.haikuowuya.sport.util.BitmapUtils;
import com.haikuowuya.sport.util.PhotoUtils;
import com.haikuowuya.sport.util.StorageUtils;

import java.util.LinkedList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Steven on 2015/11/3 0003.
 */
public class MenuFragment extends BaseFragment
{
    public static MenuFragment newInstance()
    {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    /***
     * 底部的功能菜单列表
     */
    @Bind(R.id.rv_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.civ_photo)
    CircularImageView mCircularImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(new MenuRecyclerAdapter(mActivity, genMockData()));
    }

    /**
     * 点击头像事件
     */
    @OnClick(R.id.civ_photo)
    void onPhotoClcik()
    {
        PhotoUtils.showSelectDialog(mActivity);
    }

    @Override
    public String getFragmentTitle()
    {
        return "菜单";
    }

    private LinkedList<MenuRecyclerAdapter.MenuItem> genMockData()
    {
        LinkedList<MenuRecyclerAdapter.MenuItem> items = new LinkedList<>();
        items.add(new MenuRecyclerAdapter.MenuItem(R.mipmap.ic_launcher, "精选"));
        items.add(new MenuRecyclerAdapter.MenuItem(R.mipmap.ic_launcher, "商品"));
        items.add(new MenuRecyclerAdapter.MenuItem(R.mipmap.ic_launcher, "付款"));
        items.add(new MenuRecyclerAdapter.MenuItem(R.mipmap.ic_launcher, "社区"));
        items.add(new MenuRecyclerAdapter.MenuItem(R.mipmap.ic_launcher, "免费电话"));
        items.add(new MenuRecyclerAdapter.MenuItem(R.mipmap.ic_launcher, "关于本店"));
        return items;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            String imaegFilePath = null;
            if (requestCode == PhotoUtils.REQUEST_FROM_PHOTO)
            {
                if (null != data && data.getData() != null)
                {
                    imaegFilePath = StorageUtils.getFilePathFromUri(mActivity, data.getData());
                }
            } else if (requestCode == PhotoUtils.REQUEST_FROM_CAMERA)
            {
                imaegFilePath = PhotoUtils.getFinalCameraImagePath();
            }
            if (null != imaegFilePath)
            {
                imaegFilePath = BitmapUtils.getCompressBitmapFilePath(mActivity, imaegFilePath);
                Bitmap bitmap = BitmapUtils.scaleBitmap(imaegFilePath);
                bitmap = BitmapUtils.rotateBitmap(imaegFilePath, bitmap);
                mCircularImageView.setImageBitmap(bitmap);
            } else
            {
                mActivity.showToast("图片选取失败");
            }
        }
    }
}
