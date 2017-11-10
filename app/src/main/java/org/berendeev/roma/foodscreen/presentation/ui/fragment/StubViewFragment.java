package org.berendeev.roma.foodscreen.presentation.ui.fragment;

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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.AnimationHandler;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;
import static org.berendeev.roma.foodscreen.presentation.ui.activity.ActivityContainer.FRAGMENT_CONTAINER;
import static org.berendeev.roma.foodscreen.presentation.ui.fragment.DummyViewFragment.ADD;

public class StubViewFragment extends Fragment implements FoodMenuView, AnimationHandler {

    public static final String MESSAGE = "message";
    @BindView(R.id.stub_message) TextView tvMessage;
    @BindView(R.id.test_button) Button testButton;
    @BindView(R.id.test_view_pager) Button testViewPager;
    private String messageString;
    private boolean enableAnimation = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readData();
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stub_layout, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        ButterKnife.bind(this, view);

        tvMessage.setText(messageString);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragmentById = fragmentManager.findFragmentById(FRAGMENT_CONTAINER);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slight_fade_in, R.anim.animate_nothing,
                        R.anim.animate_nothing, R.anim.slight_fade_out);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.hide(fragmentById);
                fragmentTransaction.add(FRAGMENT_CONTAINER, new DummyViewFragment(), "dummy");
                fragmentTransaction.addToBackStack(ADD);
                fragmentTransaction.commit();
//                Handler handler = new Handler(Looper.myLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        fragmentManager.beginTransaction().remove(fragmentById).addToBackStack(null).commit();
//                    }
//                }, 1000);
            }
        });

        testViewPager.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragmentById = fragmentManager.findFragmentById(FRAGMENT_CONTAINER);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slight_fade_in, R.anim.animate_nothing,
                        R.anim.animate_nothing, R.anim.slight_fade_out);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.hide(fragmentById);
                fragmentTransaction.add(FRAGMENT_CONTAINER, new FragmentManagmentFragment(), "dummy");
                fragmentTransaction.addToBackStack(ADD);
                fragmentTransaction.commit();
            }
        });
    }

    private void readData() {
        if (getArguments() != null){
            messageString = getArguments().getString(MESSAGE);
        }else {
            messageString = "LoremIpsum";
        }
    }

    public static StubViewFragment getInstance(String message) {
        StubViewFragment fragment = new StubViewFragment();
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
