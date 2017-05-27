package org.berendeev.roma.foodscreen.presentation;

import android.app.Application;

import org.berendeev.roma.foodscreen.di.DaggerMainComponent;
import org.berendeev.roma.foodscreen.di.MainComponent;


public class App extends Application {


    private static MainComponent mainComponent;

    @Override public void onCreate() {
        super.onCreate();
        initMainComponent();
    }

    private void initMainComponent() {
        mainComponent = DaggerMainComponent.builder().build();
    }

    public static MainComponent getMainComponent(){
        return mainComponent;
    }
}
