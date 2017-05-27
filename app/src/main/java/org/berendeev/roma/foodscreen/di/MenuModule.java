package org.berendeev.roma.foodscreen.di;

import org.berendeev.roma.foodscreen.presentation.ui.router.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MenuModule {

    @Singleton
    @Provides
    public Navigator provideNavigator(){
        return new Navigator();
    }
}
