package com.example.shata.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsItem>> {

    private final int NEWS_LOADER_ID = 1;
    String URL_String = "http://content.guardianapis.com/search?q=debates&api-key=test";
    NewsAdapter adapter;


    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    @Override
    public Loader<ArrayList<NewsItem>> onCreateLoader(int i, Bundle bundle) {
        // TODO: Create a new loader for the given URL
        return new NewsLoader(this, URL_String);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsItem>> loader, ArrayList<NewsItem> news) {

        if (news == null || news.isEmpty()) {
            TextView no_inter = (TextView) findViewById(R.id.no_internent);
            no_inter.setText("No Data found");
            no_inter.setVisibility(View.VISIBLE);

            return;

        }
        updateUi(news);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsItem>> loader) {
    }


    public void updateUi(final ArrayList<NewsItem> news) {

        if (news == null) {
            TextView no_inter = (TextView) findViewById(R.id.no_internent);
            no_inter.setText("No Data found");
            no_inter.setVisibility(View.VISIBLE);
        } else {
            adapter = new NewsAdapter(MainActivity.this, news);
            final ListView listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsItem item_clicked = news.get(position);
                    Intent browserIntent =
                            new Intent(Intent.ACTION_VIEW, Uri.parse(item_clicked.getUrl()));
                    startActivity(browserIntent);

                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = (Button) findViewById(R.id.ser);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isNetworkOnline()) {
                    TextView no_inter = (TextView) findViewById(R.id.no_internent);
                    no_inter.setVisibility(View.VISIBLE);
                    Log.e("Noo", "internet");

                } else {

                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.initLoader(NEWS_LOADER_ID, null, MainActivity.this);

                }
            }
        });

        if (!isNetworkOnline()) {
            TextView no_inter = (TextView) findViewById(R.id.no_internent);
            no_inter.setVisibility(View.VISIBLE);
            Log.e("Noo", "internet");
        } else {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, MainActivity.this);
        }
    }


}
