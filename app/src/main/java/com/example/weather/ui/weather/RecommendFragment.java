package com.example.weather.ui.weather;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.weather.R;

public class RecommendFragment extends Fragment {

    private ListView listView;

    public RecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_recommend, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);


    }
}
