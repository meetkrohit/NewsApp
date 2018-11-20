package com.news.app.adapters;

/**
 * Created by Rohit on 10-05-2016.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.app.R;
import com.news.app.model.NewsDetails;
import com.news.app.utils.GlobalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private GlobalUtils.ItemClikListener mItemClikListener;

    private final Context context;
    private List<NewsDetails> newsDetails;
    private ArrayList<NewsDetails> arraylist = new ArrayList<>();
    private Activity activity;
    /*private final Integer[] images;*/
   // private final String[] clinicName;
    public NewsListAdapter(Context mContext, List<NewsDetails> newsDetails, Activity activity) {
        this.context = mContext;
        this.newsDetails = newsDetails;
        arraylist.addAll(newsDetails);
        this.activity = activity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);

        NewsListItemholder vh = new NewsListItemholder(v, mItemClikListener);

        return vh;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NewsListItemholder) {
            ((NewsListItemholder) holder).newsTitle.setText(newsDetails.get(position).getTitle());

            final String news_image = newsDetails.get(position).getUrlToImage();
            Picasso.with(context).load(news_image.replace(" ", "%20")).placeholder(R.drawable.newsicon).into(((NewsListItemholder) holder).newsImage);

        }
    }

    @Override
    public int getItemCount() {
        return newsDetails.size();
    }

    public void setOnItemClikListener(GlobalUtils.ItemClikListener listener) {
        this.mItemClikListener = listener;
    }

    /*public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        bikeDataList.clear();
        if (charText.length() == 0) {
            bikeDataList.addAll(arraylist);
        } else {
            for (BikeData wp : arraylist) {

                //String name = wp.getManf_name() + wp.getModel_name();
                String name2 = wp.getBikeName();

                if (wp.getBikeName().toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.getBikeName().toLowerCase(Locale.getDefault())
                        .contains(charText) || name2.toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    bikeDataList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }*/
}
