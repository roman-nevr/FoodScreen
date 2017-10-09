package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodListView;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodMenuView;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.FoodViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodMenuFragment extends MvpAppCompatFragment implements FoodMenuView {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    private FoodViewPagerAdapter pagerAdapter;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_menu, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);

        pagerAdapter = new FoodViewPagerAdapter(getChildFragmentManager(), getTitleList(tabLayout));

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabLayout.setElevation(40.0f);
        }

    }

    private List<String> getTitleList(TabLayout tabLayout) {
        List<String> titles = new ArrayList<>();
        for (int index = 0; index < tabLayout.getTabCount(); index++) {
            titles.add(tabLayout.getTabAt(index).getText().toString());
        }
        return titles;
    }
}
