package com.yeniyoo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeniyoo.Core.CustomAdapter;
import com.yeniyoo.Model.Round;

import java.util.List;

/**
 * Created by Mathpresso2 on 2015-09-10.
 */
public class RoundAdapter extends CustomAdapter<Round, RecyclerView.ViewHolder> {
    onPickListener mListener;

    public interface onPickListener {
        void onYes(Round round);

        void onNo(Round round);
    }

    public RoundAdapter(Context context, List<Round> data, onPickListener listener) {
        super(context, data);
        mListener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        viewHolder = new RoundViewHolder(
                inflater.inflate(R.layout.fragment_round, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Round item = mItems.get(position);

    }

    public static class RoundViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public RoundViewHolder(View v) {
            super(v);
            mView = itemView;
        }
    }
}
