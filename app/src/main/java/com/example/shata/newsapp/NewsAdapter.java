package com.example.shata.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Shata on 18/07/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {
    public NewsAdapter(@NonNull Context context, ArrayList<NewsItem> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        NewsItem currentNews = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.title);

        nameTextView.setText(currentNews.getTitle());

        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);

        sectionTextView.setText(currentNews.getSection());

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        if (currentNews.getAuthor() != null) {
            author.setText(currentNews.getAuthor());
            author.setVisibility(View.VISIBLE);
        }

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        if (currentNews.getDate() != null) {
            date.setText(currentNews.getDate());
            date.setVisibility(View.VISIBLE);
        }


        return listItemView;
    }
}
