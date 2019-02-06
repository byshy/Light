package com.byshy.light.Fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.byshy.light.Adapters.WebBannerAdapter;
import com.byshy.light.Customs.BannerLayout;
import com.byshy.light.R;
import com.byshy.light.Activities.SearchActivity;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private BannerLayout recyclerBanner;
    private TextView mSearchBar;
    private FrameLayout mSearchLayoutCont;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.main_screen_content, container, false);

        recyclerBanner = root.findViewById(R.id.recycler);
        mSearchBar = root.findViewById(R.id.main_screen_search_bar);
        mSearchLayoutCont = root.findViewById(R.id.search_container);

        mSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                Pair pair = new Pair<View, String>(mSearchLayoutCont, "search_bar");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        getActivity(),
                        pair);
                startActivity(searchIntent, options.toBundle());
            }
        });

        List<String> list = new ArrayList<>();
        list.add("https://www.sciencedaily.com/images/2014/02/140223215134_1_540x360.jpg");
        list.add("https://www.sciencedaily.com/images/2014/02/140223215134_1_540x360.jpg");
        list.add("https://www.sciencedaily.com/images/2014/02/140223215134_1_540x360.jpg");
        list.add("https://www.sciencedaily.com/images/2014/02/140223215134_1_540x360.jpg");
        list.add("https://www.sciencedaily.com/images/2014/02/140223215134_1_540x360.jpg");
        list.add("https://www.sciencedaily.com/images/2014/02/140223215134_1_540x360.jpg");
        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(getContext(), list);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "a click!", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerBanner.setAdapter(webBannerAdapter);

        return root;
    }

}
