package org.berendeev.roma.foodscreen.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.domain.model.FoodItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListAdapter extends MvpBaseAdapter<FoodListAdapter.FoodViewHolder> {

    private List<FoodItem> products;

    public FoodListAdapter(MvpDelegate<?> parentDelegate, List<FoodItem> products) {
//        super(parentDelegate, String.valueOf(0)); //wtf String.valueOf(0) ?
        this.products = products;
        hasStableIds();
    }

    @Override public long getItemId(int position) {
        return products.get(position).hashCode();
    }

    @Override public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate(R.layout.food_menu_item, parent);
        return new FoodViewHolder(view);
    }

    @Override public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.product.setText(products.get(position).name());
    }

    @Override public int getItemCount() {
        return products.size();
    }

    public void update(List<FoodItem> foodItems) {
        products = foodItems;
        notifyDataSetChanged();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.product) TextView product;

        public FoodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
