package com.yeniyoo;

/**
 * Created by YJLaptop on 2016-08-13.
 */

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RoundFragment extends Fragment {

    @Bind(R.id.txtvContent)
    TextView txtvContent;

    @Bind(R.id.imgvBackgroundImage)
    SimpleDraweeView imgvBack;

    public RoundFragment() {
    }

    public static Fragment newInstance(String title, String image) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("image", image);
        RoundFragment fragment = new RoundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_round, container, false);
        ButterKnife.bind(this, view);
        initializeLayout();
        return view;
    }

    private void initializeLayout() {
        txtvContent.setText(getTitle());
        imgvBack.setImageURI(Uri.parse(getUrl()));
    }

    public String getTitle() {
        return getArguments().getString("title");
    }

    public String getUrl() {
        return getArguments().getString("image");
    }
}