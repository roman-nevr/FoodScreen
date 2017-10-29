package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.Glide;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.domain.model.BonusAction;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodListView;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodMenuView;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.BonusActionAdapter;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.CustomScroller;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.FoodViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodMenuFragment extends Fragment implements FoodMenuView {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
//    @BindView(R.id.promo_action)
    RecyclerView bonusActionRecycler;

    private FoodViewPagerAdapter pagerAdapter;

    private BonusActionAdapter actionAdapter;

    private boolean foodMenu2 = false;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (foodMenu2){
            view = inflater.inflate(R.layout.food_menu2, container, false);
        }else {
            view = inflater.inflate(R.layout.food_menu, container, false);

        }
        initUi(view);
//        ((CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar)).setContentScrim(null);
//        ((CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar)).setStatusBarScrim(null);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);

        pagerAdapter = new FoodViewPagerAdapter(getChildFragmentManager(), getTitleList(tabLayout));

        viewPager.setAdapter(pagerAdapter);
        CustomScroller.setupPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
//        viewPager.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (viewPager != null) {
//                    viewPager.setOffscreenPageLimit(3);
//                }
//            }
//        }, 500);

        tabLayout.setupWithViewPager(viewPager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabLayout.setElevation(40.0f);
        }
        actionAdapter = new BonusActionAdapter(getContext(), Glide.with(this));
        actionAdapter.setDataList(getBonusActionsList());
        if (foodMenu2){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            bonusActionRecycler.setLayoutManager(linearLayoutManager);
            bonusActionRecycler.setAdapter(actionAdapter);
        }

        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int a = 0;
                viewPager = viewPager;
            }
        });
    }

    private List<BonusAction> getBonusActionsList() {
        List<BonusAction> bonusActions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            bonusActions.add(BonusAction.create("Акция", "https://wedevstorage.blob.core.windows.net/dev/Img/BonusActionBanners/Gallery/c18921ab2a1246bb83dcd2f76057fa02.jpeg"));
        }
        return bonusActions;
    }

    private List<String> getTitleList(TabLayout tabLayout) {
        List<String> titles = new ArrayList<>();
        for (int index = 0; index < tabLayout.getTabCount(); index++) {
            titles.add(tabLayout.getTabAt(index).getText().toString());
        }
        return titles;
    }
}
