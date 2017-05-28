package org.berendeev.roma.foodscreen.presentation.ui.activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.App;
import org.berendeev.roma.foodscreen.presentation.mvp.presenter.RootViewPresenter;
import org.berendeev.roma.foodscreen.presentation.mvp.view.RootView;
import org.berendeev.roma.foodscreen.presentation.ui.router.Navigator;

import javax.inject.Inject;

public class MainActivity extends MvpAppCompatActivity implements RootView{

    private BottomNavigationView navigation;

    @Inject Navigator navigator;

    @InjectPresenter RootViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDi();
        initNavigation();

    }

    private void initDi() {
        App.getMainComponent().inject(this);
        navigator.setFragmentManger(getSupportFragmentManager());
    }

    private void initNavigation() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            navigation.setElevation(40f);
        }
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
}
