package org.berendeev.roma.foodscreen.di;

import org.berendeev.roma.foodscreen.domain.FoodRepository;
import org.berendeev.roma.foodscreen.presentation.ui.activity.ActivityContainer;
import org.berendeev.roma.foodscreen.presentation.ui.activity.MainActivity;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.FoodListFragment;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.MainScreenFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class, MenuModule.class})
public interface MainComponent {

    void inject(FoodListFragment foodListFragment);

    void inject(MainActivity activity);
    void inject(ActivityContainer activityContainer);

    FoodRepository foodRepository();

    void inject(MainScreenFragment mainScreenFragment);
}
