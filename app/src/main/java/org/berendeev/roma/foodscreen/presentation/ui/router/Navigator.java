package org.berendeev.roma.foodscreen.presentation.ui.router;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.FoodMenuFragment;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.StubViewFragment;

public class Navigator implements Router {

    public static final String MENU = "menu";
    public static final String PROFILE = "profile";
    public static final String PIZZERIAS = "pizzerias";
    public static final String CART = "cart";

    public static final int MENU_ID = 1;
    public static final int PROFILE_ID = 2;
    public static final int PIZZERIAS_ID = 3;
    public static final int CART_ID = 4;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    public void setFragmentManger(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override public void showMenu(int from) {
        if (fragmentManager.findFragmentById(R.id.container) == null){
            addFirstFragment();
        }else {
            showFragment(MENU, from, MENU_ID);
        }
    }

    private void addFirstFragment() {
        beginTransaction();
        addFragment(MENU);
        commitTransaction();
    }

    @Override public void showProfile(int from) {
        showFragment(PROFILE, from, PROFILE_ID);
    }

    @Override public void showPizzerias(int from) {
        showFragment(PIZZERIAS, from, PIZZERIAS_ID);
    }

    @Override public void showCart(int from) {
        showFragment(CART, from, CART_ID);
    }

    private void showFragment(String tag, int from, int to){
        if(from == to){
            return;
        }
        beginTransaction();
        setAnimation(from, to);
        showEnterFragment(tag);
        commitTransaction();
    }

    private void beginTransaction(){
        transaction = fragmentManager.beginTransaction();
    }

    private void commitTransaction(){
        transaction.commit();
    }

    private void setAnimation(int from, int to){
        if(from > to){
            transaction.setCustomAnimations(R.anim.to_left_in, R.anim.to_right_out);
        }else {
            transaction.setCustomAnimations(R.anim.to_right_in, R.anim.to_left_out);
        }
    }

    private void showEnterFragment(String tag) {
        transaction.replace(R.id.container, getFragment(tag), tag);
    }

    private void addFragment(String tag) {
        transaction.add(R.id.container, getFragment(tag), tag);
    }

    @NonNull private Fragment getFragment(String tag) {
        switch (tag){
            case MENU:
                return new FoodMenuFragment();
            case PIZZERIAS:
                return StubViewFragment.getInstance("Пиццерии");
            case PROFILE:
                return StubViewFragment.getInstance("Профайл");
            case CART:
                return StubViewFragment.getInstance("Корзина");
            default:
                throw new IllegalArgumentException();
        }
    }
}
