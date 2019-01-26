package com.byshy.light.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import com.byshy.light.Adapters.WebBannerAdapter;
import com.byshy.light.Customs.BannerLayout;
import com.byshy.light.R;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.main_screen_content, container, false);

        BannerLayout recyclerBanner = root.findViewById(R.id.recycler);

        List<String> list = new ArrayList<>();
        list.add("https://i.stack.imgur.com/kQAvs.png");
        list.add("https://i.stack.imgur.com/kQAvs.png");
        list.add("https://i.stack.imgur.com/kQAvs.png");
        list.add("https://i.stack.imgur.com/kQAvs.png");
        list.add("https://i.stack.imgur.com/kQAvs.png");
        list.add("https://i.stack.imgur.com/kQAvs.png");
        list.add("https://i.stack.imgur.com/kQAvs.png");
        WebBannerAdapter webBannerAdapter=new WebBannerAdapter(getContext(),list);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "a click!", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerBanner.setAdapter(webBannerAdapter);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return root;

    }

}
