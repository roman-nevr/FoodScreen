package org.berendeev.roma.foodscreen.data;


import org.berendeev.roma.foodscreen.domain.FoodRepository;
import org.berendeev.roma.foodscreen.domain.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FoodRepositoryImpl implements FoodRepository {

    public static final String IMAGE_URL = "https://wedevstorage.blob.core.windows.net/dev/Img/Products/Pizza/ru-RU/00f101db-3bab-404a-b4ef-707852cc9b2c.jpg";

    @Override public Observable<List<FoodItem>> getFoodListObservable(String type) {
        return Observable.fromCallable(() -> {
            //todo remove stub
            List<FoodItem> foodItems = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                FoodItem foodItem = FoodItem.create(String.valueOf(i) + " -- " + type, IMAGE_URL);
                foodItems.add(foodItem);
            }
            return foodItems;
        });
    }
}
