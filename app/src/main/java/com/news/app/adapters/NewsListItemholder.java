package com.news.app.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.app.R;
import com.news.app.utils.GlobalUtils;

public class NewsListItemholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private GlobalUtils.ItemClikListener mListener;
    TextView newsTitle;
    ImageView newsImage;

    public NewsListItemholder(View itemView, GlobalUtils.ItemClikListener listener) {
        super(itemView);

        newsTitle = (TextView) itemView.findViewById(R.id.news_title);
        newsImage = (ImageView) itemView.findViewById(R.id.news_image);
        mListener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mListener.onItemClick(v, getItemViewType(), getAdapterPosition());

    }

}
