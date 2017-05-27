package org.berendeev.roma.foodscreen.di;

import org.berendeev.roma.foodscreen.data.FoodRepositoryImpl;
import org.berendeev.roma.foodscreen.domain.FoodRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Singleton
    @Provides
    public FoodRepository provideFoodRepository(){
        return new FoodRepositoryImpl();
    }
}
