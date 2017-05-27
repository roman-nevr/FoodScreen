package org.berendeev.roma.foodscreen.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.berendeev.roma.foodscreen.domain.FoodRepository;
import org.berendeev.roma.foodscreen.presentation.App;
import org.berendeev.roma.foodscreen.presentation.mvp.view.FoodListView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FoodListPresenter extends MvpPresenter<FoodListView> {

    private final FoodRepository repository;
    private String type;
    private final CompositeDisposable compositeDisposable;

    public FoodListPresenter() {
        repository = App.getMainComponent().foodRepository();
        compositeDisposable = new CompositeDisposable();
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override protected void onFirstViewAttach() {
        subscribeOnFoodList();
    }

    @Override public void onDestroy() {
        compositeDisposable.clear();
    }

    private void subscribeOnFoodList() {
        compositeDisposable.add(repository.getFoodListObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(foodItems -> {
                    getViewState().showList(foodItems);
                }));
    }
}
