package org.berendeev.roma.foodscreen.presentation.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.berendeev.roma.foodscreen.presentation.ui.fragment.FoodListFragment;

import java.util.List;


public class FoodViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles;

    public FoodViewPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override public Fragment getItem(int position) {
        return FoodListFragment.getInstance(titles.get(position));
    }

    @Override public int getCount() {
        return titles.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
