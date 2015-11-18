package com.haikuowuya.sport.adapter.appointment;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haikuowuya.sport.R;
import com.haikuowuya.sport.model.GymItem;

import java.util.List;

/**
 * 首页预约Recycler的适配器
 */
public class AppointmentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<GymItem> mGymItems;

    public AppointmentRecyclerAdapter(List<GymItem> gymItems)
    {
        mGymItems = gymItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_appointment_list_item, null);
        return new AppointmentRecyclerAdapter.ListItemVH(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof ListItemVH)
        {
            ListItemVH listItemVH = (ListItemVH) holder;
            GymItem gymItem = mGymItems.get(position);
            listItemVH.mTvGymName.setText(gymItem.gymName);
            listItemVH.mTvCircleBusiness.setText(gymItem.circleBusiness);
            String textPerPerson = "人均：";
            String textYuan = "元";
            SpannableStringBuilder spannableStringBuilderPrice = new SpannableStringBuilder(textPerPerson + gymItem.perPrice + textYuan);
            spannableStringBuilderPrice.setSpan(new ForegroundColorSpan(0xFF0FC5C3), textPerPerson.length(), textPerPerson.length() + gymItem.perPrice.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            spannableStringBuilderPrice.setSpan(new RelativeSizeSpan(1.5f), textPerPerson.length(), textPerPerson.length() + gymItem.perPrice.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            listItemVH.mTvPrice.setText(spannableStringBuilderPrice);
            String textUsableHint = " 张闲置卡可用";
            SpannableStringBuilder spannableStringBuilderUsable = new SpannableStringBuilder(gymItem.usableCount + textUsableHint);
            spannableStringBuilderUsable.setSpan(new ForegroundColorSpan(0xFF0FC5C3), 0, gymItem.usableCount.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            listItemVH.mTvUsableCount.setText(spannableStringBuilderUsable);
        }
    }

    @Override
    public int getItemCount()
    {
        return mGymItems.size();
    }

    public static class ListItemVH extends RecyclerView.ViewHolder
    {
        private View mItemView;
        private TextView mTvPrice;
        private TextView mTvCircleBusiness;
        private TextView mTvGymName;
        private TextView mTvUsableCount;

        public ListItemVH(View itemView)
        {
            super(itemView);
            mItemView = itemView;
            mTvCircleBusiness = (TextView) itemView.findViewById(R.id.tv_circle_business);
            mTvGymName = (TextView) itemView.findViewById(R.id.tv_gym_name);
            mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            mTvUsableCount = (TextView) itemView.findViewById(R.id.tv_usable_count);
        }
    }
}
