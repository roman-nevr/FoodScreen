package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.AnimationHandler;
import org.berendeev.roma.foodscreen.presentation.App;
import org.berendeev.roma.foodscreen.presentation.mvp.presenter.RootViewPresenter;
import org.berendeev.roma.foodscreen.presentation.mvp.view.RootView;
import org.berendeev.roma.foodscreen.presentation.ui.router.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roma on 24.09.17.
 */

public class MainScreenFragment extends Fragment implements RootView{

    @Inject
    Navigator navigator;

    @InjectPresenter
    RootViewPresenter presenter;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        initDi();
        initNavigator();
        initNavigation();
        return view;
    }

    private void initDi() {
        App.getMainComponent().inject(this);
    }

    private void initNavigator() {
        navigator.initNavigator(getChildFragmentManager(), R.id.main_container);
    }

    private void initNavigation() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            navigation.setElevation(40f);
        }
        navigator.showMenu(1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = navigation.getSelectedItemId();
            int from = navigation.getMenu().findItem(id).getOrder();

            switch (item.getItemId()) {
                case R.id.menu:
                    navigator.showMenu(from);
                    return true;
                case R.id.profile:
                    navigator.showProfile(from);
                    return true;
                case R.id.pizzerias:
                    navigator.showPizzerias(from);
                    return true;
                case R.id.cart:
                    navigator.showCart(from);
                    return true;
            }
            return false;
        }
    };

    @Override public void showMenu(int from) {
        navigator.showMenu(from);
    }

    @Override public void showProfile(int from) {
        navigator.showProfile(from);
    }

    @Override public void showPizzerias(int from) {
        navigator.showPizzerias(from);
    }

    @Override public void showCart(int from) {
        navigator.showCart(from);
    }

    @Override
    public void onStop() {
        super.onStop();
        Fragment fragmentById = getChildFragmentManager().findFragmentById(R.id.main_container);
        if (fragmentById instanceof AnimationHandler){
            AnimationHandler animationHandler = (AnimationHandler) fragmentById;
            animationHandler.enableAnimation(false);
        }
    }

    @Override
    public void onStart() {
        Fragment fragmentById = getChildFragmentManager().findFragmentById(R.id.main_container);
        if (fragmentById instanceof AnimationHandler){
            AnimationHandler animationHandler = (AnimationHandler) fragmentById;
            animationHandler.enableAnimation(true);
        }
        super.onStart();
    }

//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        if (!enter){
//            return AnimationUtils.loadAnimation(getContext(), nextAnim);
//        }else {
//            return super.onCreateAnimation(transit, enter, nextAnim);
//        }
//    }
}
