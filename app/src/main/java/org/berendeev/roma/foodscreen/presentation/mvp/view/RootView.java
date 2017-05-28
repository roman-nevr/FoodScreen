package org.berendeev.roma.foodscreen.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import org.berendeev.roma.foodscreen.presentation.ui.router.Router;

@StateStrategyType(SkipStrategy.class)
public interface RootView extends MvpView, Router{

}
