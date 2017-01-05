package com.speedata.applicationlock.manager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends BaseFragment {


    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

}
