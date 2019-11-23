package com.example.designmaterials.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designmaterials.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewsFragment extends Fragment {


    public ViewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_views, container, false);

        ((ViewPager) view.findViewById(R.id.layoutViewPager)).setAdapter(new LayoutViewPagerAdapter(getChildFragmentManager()));

        return view;
    }

    private class LayoutViewPagerAdapter extends FragmentPagerAdapter {

        private int[] layouts = {R.layout.layout_view_one, R.layout.layout_view_two};

        public LayoutViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position >= layouts.length || position < 0) {
                return ViewFragment.newInstance(R.layout.fragment_view);
            }
            return ViewFragment.newInstance(layouts[position]);
        }

        @Override
        public int getCount() {
            return layouts.length;
        }
    }

}
