package com.haikuowuya.sport.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.core.util.DensityUtils;
import com.haikuowuya.sport.R;
import com.haikuowuya.sport.adapter.home.group.AttentionRecyclerAdapter;
import com.haikuowuya.sport.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Steven on 2015/12/2 0002.
 */
public class AttentionFragment extends BaseFragment
{
    public static AttentionFragment newInstance()
    {
        AttentionFragment fragment = new AttentionFragment();
        return fragment;
    }

    @Bind(R.id.rv_recycler_view)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_attention, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(new AttentionRecyclerAdapter());
        final int paddingBottom = DensityUtils.dpToPx(mActivity, 6.f);
        RecyclerView.ItemDecoration decor = new RecyclerView.ItemDecoration()
        {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
            {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, paddingBottom);
            }
        };
        mRecyclerView.addItemDecoration(decor);
    }

    @Override
    public String getFragmentTitle()
    {
        return "关注";
    }
}
