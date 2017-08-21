package com.example.shata.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Shata on 18/07/2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsItem>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<NewsItem> loadInBackground() {
        if (mUrl == null)
            return null;

        ArrayList<NewsItem> NewsItem = Utilis.fetchNewsData(mUrl);
        return NewsItem;
    }
}
