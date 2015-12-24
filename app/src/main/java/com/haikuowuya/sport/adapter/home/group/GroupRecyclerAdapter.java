package com.haikuowuya.sport.adapter.home.group;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haikuowuya.sport.R;

/**
 * Created by Steven on 2015/12/24 0024.
 */
public class GroupRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_group_list_item, null);
        ListViewVH listViewVH = new ListViewVH(itemView);
        return listViewVH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 20;
    }

    public static class ListViewVH extends RecyclerView.ViewHolder
    {
        public ListViewVH(View itemView)
        {
            super(itemView);
        }
    }
}
