package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.AnimationHandler;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodMenuView;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.FragmentManagmentFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;
import static org.berendeev.roma.foodscreen.presentation.ui.activity.ActivityContainer.FRAGMENT_CONTAINER;

public class FragmentManagmentFragment extends MvpAppCompatFragment implements FoodMenuView {

    @BindView(R.id.add_fragment) Button addFragmentButton;
    @BindView(R.id.remove_fragment) Button removeFragmentButton;
    @BindView(R.id.refresh) Button refreshButton;
    @BindView(R.id.tab_layout) TabLayout tabs;

    @BindView(R.id.view_pager) ViewPager viewpager;

    FragmentManagmentFragmentAdapter adapter;
    private List<String> titles;
    private List<Integer> objects;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_managment_fragmant, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);
        titles = new ArrayList<>();
        objects = new ArrayList<>();
        for (int index = 0; index < 3; index++) {
            addSet(index);
        }
        adapter = new FragmentManagmentFragmentAdapter(getChildFragmentManager(), getContext(), titles, objects);
        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager);

        addFragmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int number = 0;
                for (Integer object : objects) {
                    if (number < object) {
                        number = object;
                    }
                }
                number++;
                addSet(number);
                adapter.update(viewpager.getCurrentItem(), objects, titles);
            }
        });

        removeFragmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int index = viewpager.getCurrentItem();
                removeSet(index);
                adapter.update(viewpager.getCurrentItem(), objects, titles);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                titles = new ArrayList<>(titles);
                objects = new ArrayList<>(objects);
                adapter.update(viewpager.getCurrentItem(), objects, titles);
            }
        });

    }

    private void addSet(Integer index){
        titles = new ArrayList<>(titles);
        objects = new ArrayList<>(objects);
        titles.add(0, String.valueOf(index));
        objects.add(0, index);
    }

    private void removeSet(int index){
        titles = new ArrayList<>(titles);
        objects = new ArrayList<>(objects);
        titles.remove(index);
        objects.remove(index);
    }

    public static FragmentManagmentFragment getInstance(String message) {
        FragmentManagmentFragment fragment = new FragmentManagmentFragment();
        return fragment;
    }
}
