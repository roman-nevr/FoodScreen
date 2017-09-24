package org.berendeev.roma.foodscreen.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.presentation.App;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.MainScreenFragment;

/**
 * Created by roma on 24.09.17.
 */

public class ActivityContainer extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        initDi();
        int containerId = R.id.main_container;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, new MainScreenFragment(), "main");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initDi() {
        App.getMainComponent().inject(this);
    }
}
