package com.byshy.light.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.byshy.light.Models.SearchResult;
import com.byshy.light.R;
import java.util.ArrayList;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder> {

    private ArrayList<SearchResult> mData;
    private Context mContext;

    public SearchResultsAdapter(ArrayList<SearchResult> data, Context context) {
        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_item, viewGroup, false);
        return new SearchResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder searchResultViewHolder, int i) {
        final SearchResult searchResult = mData.get(i);
        searchResultViewHolder.result.setText(searchResult.getContent());
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) searchResultViewHolder.result.getLayoutParams();

        searchResultViewHolder.backBar.setVisibility(View.GONE);
        searchResultViewHolder.setClickable(true);
        params.leftMargin = 16;
        searchResultViewHolder.result.setTypeface(null, Typeface.NORMAL);
        if (searchResult.getType() == 1) {
            searchResultViewHolder.backBar.setVisibility(View.VISIBLE);
            searchResultViewHolder.setClickable(false);
            params.leftMargin = 32;
            searchResultViewHolder.result.setTypeface(null, Typeface.BOLD);
        }

        if (searchResult.getType() == 0) {
            searchResultViewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, searchResult.getContent(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {

        private TextView result;
        private LinearLayout backBar;
        private View view;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            result = itemView.findViewById(R.id.search_result);
            backBar = itemView.findViewById(R.id.search_item_back_bar);
        }

        public void setClickable(boolean clickable) {
            view.setClickable(clickable);
            view.setFocusable(clickable);
        }

    }

}
