package org.berendeev.roma.foodscreen.di;

import org.berendeev.roma.foodscreen.domain.FoodRepository;
import org.berendeev.roma.foodscreen.presentation.ui.activity.MainActivity;
import org.berendeev.roma.foodscreen.presentation.ui.fragment.FoodListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class, MenuModule.class})
public interface MainComponent {

    void inject(FoodListFragment foodListFragment);

    void inject(MainActivity activity);

    FoodRepository foodRepository();
}
