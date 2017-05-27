package org.berendeev.roma.foodscreen.presentation.mvp.view;


import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface StubView {
    void showView(String message);
}
