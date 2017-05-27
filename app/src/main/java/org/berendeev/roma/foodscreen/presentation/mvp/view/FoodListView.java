package org.berendeev.roma.foodscreen.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import org.berendeev.roma.foodscreen.domain.model.FoodItem;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FoodListView extends MvpView {
    void showList(List<FoodItem> foodItems);
}
