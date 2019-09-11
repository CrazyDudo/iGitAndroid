package com.cd.igitandroid.ui.issue;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cd.igitandroid.R;

/**
 * issue fragment
 */
public class IssueFragment extends Fragment {


    public IssueFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_issue, container, false);
    }

}
