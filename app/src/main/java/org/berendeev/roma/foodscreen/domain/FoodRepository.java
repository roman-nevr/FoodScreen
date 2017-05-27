package org.berendeev.roma.foodscreen.domain;

import org.berendeev.roma.foodscreen.domain.model.FoodItem;

import java.util.List;

import io.reactivex.Observable;

public interface FoodRepository {
    Observable<List<FoodItem>> getFoodListObservable(String type);
}
