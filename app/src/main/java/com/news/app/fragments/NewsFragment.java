package com.news.app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.news.app.apiServices.ApiHelper;
import com.news.app.apiServices.AppConstant;
import com.news.app.apiServices.NetworkConnection;
import com.news.app.activities.NewsDetailsActivity;
import com.news.app.R;
import com.news.app.adapters.NewsListAdapter;
import com.news.app.model.NewsDetails;
import com.news.app.utils.GlobalUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsFragment extends Fragment implements GlobalUtils.ItemClikListener, GlobalUtils.ButtonClickListener {

    NetworkConnection nw;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsListAdapter newsListAdapter;
    List<NewsDetails> newsDetailsList;
    NewsDetails newsDetailsl;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        nw = new NetworkConnection(getActivity());

        if (nw.isConnectingToInternet()) {
            new ApiHelper(getActivity(), "newsResponse", AppConstant.newsApi, newsResponse).execute();
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.internet_error), Toast.LENGTH_LONG).show();
        }

        return view;
    }

    private ApiHelper.TaskDelegate newsResponse = new ApiHelper.TaskDelegate() {
        public void onTaskFisnishGettingData(JSONObject result) {
            try {
                if (result != null) {
                    if (result.get("status").equals("ok")) {
                        JSONArray jsonArray = result.getJSONArray("articles");
                        Log.e("jsonArray", jsonArray.toString());
                        newsDetailsList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            newsDetailsl = new NewsDetails();
                            JSONObject sourceObject = jsonArray.getJSONObject(i).getJSONObject("source");
                            newsDetailsl.setId(sourceObject.getString("id"));
                            newsDetailsl.setName(sourceObject.getString("name"));

                            newsDetailsl.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                            newsDetailsl.setTitle(jsonArray.getJSONObject(i).getString("title"));
                            newsDetailsl.setDescription(jsonArray.getJSONObject(i).getString("description"));
                            newsDetailsl.setUrl(jsonArray.getJSONObject(i).getString("url"));
                            newsDetailsl.setUrlToImage(jsonArray.getJSONObject(i).getString("urlToImage"));
                            newsDetailsl.setPublishedAt(jsonArray.getJSONObject(i).getString("publishedAt"));
                            newsDetailsl.setContent(jsonArray.getJSONObject(i).getString("content"));

                            newsDetailsList.add(newsDetailsl);

                        }
                    }

                    Collections.reverse(newsDetailsList);
                    Activity activity = getActivity().getParent();
                    newsListAdapter = new NewsListAdapter(getActivity(), newsDetailsList,activity);
                    mRecyclerView.setAdapter(newsListAdapter);
                    newsListAdapter.setOnItemClikListener(NewsFragment.this);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    };
        @Override
        public void onItemClick(View view, int itemType, int position) {

            Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
            intent.putExtra("newsImage", newsDetailsList.get(position).getUrlToImage());
            intent.putExtra("newsTitle",newsDetailsList.get(position).getDescription());
            getActivity().startActivity(intent);
            /*android.support.v4.util.Pair<View, String> p1 = android.support.v4.util.Pair.create(view.findViewById(R.id.news_image), "newsImage");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((MainActivity) getActivity(), p1);
            getActivity().startActivity(intent, options.toBundle());*/

        }

        @Override
        public void onButtonClick(View view, int position) {

        }

}
