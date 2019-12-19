package com.example.weather.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.weather.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class WeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private View root;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();

        tabLayout = root.findViewById(R.id.tablayout);
        viewPager = root.findViewById(R.id.viewpager);

        fragments.add(new TodayFragment());
        fragments.add(new RecommendFragment());

        titles.add("today");
        titles.add("recommend");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()){

            //获取每个页卡
            @Override
            public Fragment getItem(int position){
                return fragments.get(position);
            }

            //获取页卡数
            @Override
            public int getCount(){
                return  fragments.size();
            }

            //获取页卡标题
            @Override
            public CharSequence getPageTitle(int position){
                return titles.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        weatherViewModel =
                ViewModelProviders.of(this).get(WeatherViewModel.class);
        root = inflater.inflate(R.layout.fragment_weather, container, false);

        weatherViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }


}