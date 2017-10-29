package org.berendeev.roma.foodscreen.presentation.ui.router;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.AnimationHandler;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.FoodMenuFragment;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.StubViewFragment;

public class Navigator implements Router {

    public static final String MENU = "menu";
    public static final String PROFILE = "profile";
    public static final String PIZZERIAS = "pizzerias";
    public static final String CART = "cart";

    public static final int MENU_ID = 0;
    public static final int PROFILE_ID = 1;
    public static final int PIZZERIAS_ID = 2;
    public static final int CART_ID = 3;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private int containerId;

    public void initNavigator(FragmentManager fragmentManager, @IdRes int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    @Override public void showMenu(int from) {
        if (fragmentManager.findFragmentById(containerId) == null){
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
        Fragment previousFragment = fragmentManager.findFragmentById(containerId);
        Fragment nextFragment = fragmentManager.findFragmentByTag(tag);
        detachPreviousFragment(previousFragment);
        if (nextFragment == null){
            nextFragment = newFragment(tag);
            addNextFragment(nextFragment, tag);
        }else {
            attachNextFragment(nextFragment);
        }
//        animate(nextFragment, previousFragment, from, to);
//        showNextFragment(tag);
        commitTransaction();
    }

    private void animate(Fragment nextFragment, Fragment previousFragment, int from, int to) {
        int duration = 400;
//        if(from > to){
//            nextFragment.setEnterTransition(new Slide(Gravity.LEFT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            nextFragment.setReturnTransition(new Slide(Gravity.LEFT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            previousFragment.setExitTransition(new Slide(Gravity.RIGHT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            previousFragment.setReenterTransition(new Slide(Gravity.RIGHT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//        }else {
//            nextFragment.setEnterTransition(new Slide(Gravity.RIGHT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            nextFragment.setReturnTransition(new Slide(Gravity.RIGHT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            previousFragment.setExitTransition(new Slide(Gravity.LEFT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            previousFragment.setReenterTransition(new Slide(Gravity.LEFT).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//        }
        if(from > to){
            nextFragment.setEnterTransition(new Fade(Fade.IN).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
            nextFragment.setReturnTransition(new Fade(Fade.OUT).setDuration(duration).setInterpolator(new AccelerateInterpolator()));
            previousFragment.setExitTransition(new Fade(Fade.OUT).setDuration(duration).setInterpolator(new AccelerateInterpolator()));
            previousFragment.setReenterTransition(new Fade(Fade.IN).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
        }else {
            nextFragment.setEnterTransition(new Fade(Fade.IN).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
            nextFragment.setReturnTransition(new Fade(Fade.OUT).setDuration(duration).setInterpolator(new AccelerateInterpolator()));
            previousFragment.setExitTransition(new Fade(Fade.OUT).setDuration(duration).setInterpolator(new AccelerateInterpolator()));
            previousFragment.setReenterTransition(new Fade(Fade.IN).setDuration(duration).setInterpolator(new DecelerateInterpolator()));
        }
//        if(from > to){
//            nextFragment.setEnterTransition(new Explode().setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            nextFragment.setReturnTransition(new Explode().setDuration(duration).setInterpolator(new AccelerateInterpolator()));
//            previousFragment.setExitTransition(new Explode().setDuration(duration).setInterpolator(new AccelerateInterpolator()));
//            previousFragment.setReenterTransition(new Explode().setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//        }else {
//            nextFragment.setEnterTransition(new Explode().setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//            nextFragment.setReturnTransition(new Explode().setDuration(duration).setInterpolator(new AccelerateInterpolator()));
//            previousFragment.setExitTransition(new Explode().setDuration(duration).setInterpolator(new AccelerateInterpolator()));
//            previousFragment.setReenterTransition(new Explode().setDuration(duration).setInterpolator(new DecelerateInterpolator()));
//        }
    }

    private void beginTransaction(){
        transaction = fragmentManager.beginTransaction();
    }

    private void commitTransaction(){
        transaction.commit();
    }

    private void setAnimation(int from, int to){
        if(from > to){
            transaction.setCustomAnimations(R.anim.to_right_in, R.anim.to_right_out);
        }else {
            transaction.setCustomAnimations(R.anim.to_left_in, R.anim.to_left_out);
        }
    }

    private void detachPreviousFragment(Fragment fragment){
        transaction.detach(fragment);
    }

    private void addNextFragment(Fragment fragment, String tag){
        transaction.add(containerId, fragment, tag);
    }

    private void attachNextFragment(Fragment fragment){
        transaction.attach(fragment);
    }

//    private void showNextFragment(String tag) {
//        Fragment previousFragment = fragmentManager.findFragmentById(containerId);
//        transaction.detach(previousFragment);
//
//        Fragment nextFragment = fragmentManager.findFragmentByTag(tag);
//        if (nextFragment != null){
//            transaction.attach(nextFragment);
//        }else {
//            transaction.add(containerId, newFragment(tag), tag);
//        }
//    }

    private void addFragment(String tag) {
        transaction.add(containerId, newFragment(tag), tag);
    }

    @NonNull private Fragment newFragment(String tag) {
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
