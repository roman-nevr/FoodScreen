package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.domain.model.FoodItem;
import org.berendeev.roma.foodscreen.presentation.AnimationHandler;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodMenuView;
import org.berendeev.roma.foodscreen.presentation.ui.adapter.FoodListAdapter2;
import org.berendeev.roma.foodscreen.presentation.ui.views.CustomRVView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.berendeev.roma.foodscreen.data.FoodRepositoryImpl.IMAGE_URL;
import static org.berendeev.roma.foodscreen.presentation.ui.activity.ActivityContainer.FRAGMENT_CONTAINER;
import static org.berendeev.roma.foodscreen.presentation.ui.fragment.DummyViewFragment.ADD;

public class StubRecyclerViewFragment extends Fragment implements FoodMenuView, AnimationHandler {

    public static final String MESSAGE = "message";

    private String messageString;
    private boolean enableAnimation = true;
    private FoodListAdapter2 adapter2;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readData();
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CustomRVView customRVView = new CustomRVView(getContext());
//        View customRVView = inflater.inflate(R.layout.food_list, container, false);
        initUi(customRVView);
        return customRVView;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);
        List<FoodItem> foodItems = createItems(messageString);
        if(adapter2 == null){
            adapter2 = new FoodListAdapter2(foodItems, Glide.with(this), getContext(),false);
        }else {
            adapter2.update(foodItems);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter2);
    }

    private void initUi(CustomRVView view) {
        List<FoodItem> foodItems = createItems(messageString);
        if(adapter2 == null){
            adapter2 = new FoodListAdapter2(foodItems, Glide.with(this), getContext(), true);
        }else {
            adapter2.update(foodItems);
        }
        view.setAdapter(adapter2);
    }

    private List<FoodItem> createItems(String type){
        List<FoodItem> foodItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FoodItem foodItem = FoodItem.create(String.valueOf(i) + " -- " + type,
                    IMAGE_URL);
            foodItems.add(foodItem);
        }
        return foodItems;
    }

    private void readData() {
        if (getArguments() != null){
            messageString = getArguments().getString(MESSAGE);
        }else {
            messageString = "LoremIpsum";
        }
    }

    public static StubRecyclerViewFragment getInstance(String message) {
        StubRecyclerViewFragment fragment = new StubRecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, message);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!enableAnimation){
            StringBuilder builder = new StringBuilder("нет анимации при ");
            if (enter){
                builder.append("входе");
            }else {
                builder.append("выходе");
            }
            System.out.println(builder.toString());
            Animation animation = new AlphaAnimation(1, 1);
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

    @Override
    public void enableAnimation(boolean enable) {
        if (enable){
            System.out.println("enable animation");
        }else {
            System.out.println("disable animation");
        }
        this.enableAnimation = enable;
    }
}
