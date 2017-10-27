package org.berendeev.roma.foodscreen.presentation.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.domain.model.FoodItem;
import org.berendeev.roma.foodscreen.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListAdapter2 extends MvpBaseAdapter<FoodListAdapter2.FoodViewHolder> {

    private final RequestManager glide;
    private final Context context;
    private List<FoodItem> products;

    public FoodListAdapter2(List<FoodItem> products, RequestManager glide, Context context) {
//        super(parentDelegate, String.valueOf(0)); //wtf String.valueOf(0) ?
        this.products = products;
        this.glide = glide;
        this.context = context;
        setHasStableIds(true);
    }

    @Override public long getItemId(int position) {
        return products.get(position).hashCode();
    }

    @Override public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate(R.layout.menu_item_pizza_list, parent);
        return new FoodViewHolder(view);
    }

    @Override public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override public int getItemCount() {
        return products.size();
    }

    public void update(List<FoodItem> foodItems) {
        products = foodItems;
        notifyDataSetChanged();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.label_title) TextView title;
        @BindView(R.id.imageView) ImageView imageView;

        public FoodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(FoodItem foodItem){
            title.setText(foodItem.name());
            showImage(foodItem.url());
        }

        private void showImage(String url){

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(ImageUtils.getVectorDrawable(context, R.drawable.round_pizza))
                    .priority(Priority.HIGH);

            RequestBuilder<Drawable> requestBuilder = glide.load(url);

            requestBuilder
                    .apply(options)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(new SimpleTarget<Drawable>(){
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            imageView.setImageDrawable(resource);
                        }
                    });
        }
    }
}
