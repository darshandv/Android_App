package com.myapp.darshan.sip;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Discuss extends Fragment {
    private Context mContext;

//    RelativeLayout mRelativeLayout;
//    private RecyclerView mRecyclerView;
//
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private CardView c;

    String[] subject = {
            "DC", "SE", "OS", "DAA", "SP", "OOP"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discuss, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getContext();
        c = (CardView)view.findViewById(R.id.cardDC);
//        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        mLayoutManager = new GridLayoutManager(mContext,2);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        // Initialize a new instance of RecyclerView Adapter instance
//        mAdapter = new Adapter(mContext,subject);
//
//        // Set the adapter for RecyclerView
//        mRecyclerView.setAdapter(mAdapter);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.getContext().startActivity(new Intent(c.getContext(),DCforum.class));
            }
        });

    }
}