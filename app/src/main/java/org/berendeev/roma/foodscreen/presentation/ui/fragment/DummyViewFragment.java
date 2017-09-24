package org.berendeev.roma.foodscreen.presentation.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.AnimationHandler;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodMenuView;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class DummyViewFragment extends MvpAppCompatFragment implements FoodMenuView, AnimationHandler {

    public static final String MESSAGE = "message";
    public static final String ADD = "add";
    @BindView(R.id.stub_message) TextView tvMessage;
    @BindView(R.id.test_button) Button testButton;
    @BindView(R.id.close_button) Button closeButton;
    private String messageString;
    private boolean enableAnimation;
    private int containerId;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_layout, container, false);
        readData();
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);
        tvMessage.setText(messageString);
        containerId = R.id.main_container;
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.to_left_in, R.anim.to_left_out,
                        R.anim.to_right_in, R.anim.to_right_out);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.main_container, new DummyViewFragment(), "dummy");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentManager.BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(1);
                try {
                    Field popExitAnim = backStackEntryAt.getClass().getDeclaredField("mPopExitAnim");
                    popExitAnim.setAccessible(true);
                    popExitAnim.setInt(backStackEntryAt, R.anim.slight_fade_out);
                    popExitAnim.setAccessible(false);
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                }
                FragmentManager.BackStackEntry backStackEntryAt0 = fragmentManager.getBackStackEntryAt(0);
                try {
                    Field popExitAnim = backStackEntryAt0.getClass().getDeclaredField("mPopEnterAnim");
                    popExitAnim.setAccessible(true);
                    popExitAnim.setInt(backStackEntryAt, R.anim.animate_nothing);
                    popExitAnim.setAccessible(false);
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                }
                fragmentManager.popBackStackImmediate(ADD, POP_BACK_STACK_INCLUSIVE);
//                fragmentManager.popBackStackImmediate();
//                fragmentManager.popBackStackImmediate();
            }
        });
    }

    private void readData() {
//        messageString = getArguments().getString(MESSAGE);
        messageString = this.getClass().getSimpleName();
    }

    public static DummyViewFragment getInstance(String message) {
        DummyViewFragment fragment = new DummyViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, message);
        fragment.setArguments(bundle);
        return fragment;
    }

//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);
//        if (animation == null){
//            animation = new Animation(){};
//        }
//        if (!enableAnimation){
//            animation.setDuration(0);
//        }
//        return animation;
//    }

    @Override
    public void enableAnimation(boolean enable) {
        this.enableAnimation = enable;
    }
}
