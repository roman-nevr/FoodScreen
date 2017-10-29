package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.domain.model.FoodItem;
import org.berendeev.roma.foodscreen.presentation.AnimationHandler;
import org.berendeev.roma.foodscreen.presentation.mvp.presenter.FoodListPresenter;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodListView;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.FoodListAdapter;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.FoodListAdapter2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodListFragment extends MvpAppCompatFragment implements FoodListView {

    public static final String TYPE = "type";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @InjectPresenter FoodListPresenter presenter;

    private FoodListAdapter2 adapter2;
    private FoodListAdapter adapter;
    private String type;
    private boolean enableAnimation = true;

    private boolean useAdapter2 = true;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readData();
        presenter.setType(type);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_list, container, false);
        initUi(view);
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
       if (useAdapter2){
           setAdapter2();
       }else {
           setAdapter();
       }
    }

    private void setAdapter(){
        if (adapter != null){
            recyclerView.setAdapter(adapter);
        }
    }

    private void setAdapter2(){
        if (adapter2 != null){
            recyclerView.setAdapter(adapter2);
        }
    }

    @Override public void showList(List<FoodItem> foodItems) {
        if (useAdapter2){
            useAdapter2(foodItems);
        }else {
            useAdapter(foodItems);
        }
    }

    private void useAdapter(List<FoodItem> foodItems){
        if(adapter == null){
            adapter = new FoodListAdapter(foodItems);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.update(foodItems);
        }
    }

    private void useAdapter2(List<FoodItem> foodItems){
        if(adapter2 == null){
            adapter2 = new FoodListAdapter2(foodItems, Glide.with(this), getContext());
            recyclerView.setAdapter(adapter2);
        }else {
            adapter2.update(foodItems);
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

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!enter){
            StringBuilder builder = new StringBuilder("нет анимации при ");
            if (enter){
                builder.append("входе");
            }else {
                builder.append("выходе");
            }
            System.out.println(builder.toString());
            Animation animation = new AlphaAnimation(1, 1);
            animation.setDuration(400);
            return animation;
//            return super.onCreateAnimation(transit, enter, R.anim.animate_nothing);
        }else {
            logAnimation(enter, nextAnim);
            return super.onCreateAnimation(transit, enter, nextAnim);
        }
    }

    private void logAnimation(boolean enter, int nextAnim) {
        StringBuilder builder = new StringBuilder();
        if (enter){
            builder.append("при входе");
        }else {
            builder.append("при выходе");
        }
        builder.append(" nextAnim ");
        switch (nextAnim){
            case R.anim.to_left_in:{
                builder.append("вход налево");
                break;
            }
            case R.anim.to_left_out:{
                builder.append("выход налево");
                break;
            }
            case R.anim.to_right_in:{
                builder.append("вход направо");
                break;
            }
            case R.anim.to_right_out:{
                builder.append("выход направо");
                break;
            }
            default:break;
        }
        builder.append("\n");
        System.out.println(builder.toString());
    }
}
