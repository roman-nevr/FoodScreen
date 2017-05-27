package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.domain.model.FoodItem;
import org.berendeev.roma.foodscreen.presentation.mvp.presenter.FoodListPresenter;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodListView;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.FoodListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodListFragment extends MvpAppCompatFragment implements FoodListView {

    public static final String TYPE = "type";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @InjectPresenter FoodListPresenter presenter;

    private FoodListAdapter adapter;
    private String type;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setType(type);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_list, container, false);
        initUi(view);
        readData();
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override public void showList(List<FoodItem> foodItems) {
        if(adapter == null){
            adapter = new FoodListAdapter(getMvpDelegate(), foodItems);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.update(foodItems);
        }
    }

    private void readData() {
        type = getArguments().getString(TYPE);
    }

    public static FoodListFragment getInstance(String type) {
        FoodListFragment fragment = new FoodListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }
}
