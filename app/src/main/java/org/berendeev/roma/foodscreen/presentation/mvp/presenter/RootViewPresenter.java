package org.berendeev.roma.foodscreen.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.berendeev.roma.foodscreen.presentation.mvp.view.RootView;

@InjectViewState
public class RootViewPresenter extends MvpPresenter<RootView> {
    public RootViewPresenter() {
    }

    @Override protected void onFirstViewAttach() {
        getViewState().showMenu(1);
    }
}
