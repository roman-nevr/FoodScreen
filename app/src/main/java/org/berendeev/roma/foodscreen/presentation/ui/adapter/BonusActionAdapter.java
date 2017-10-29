package org.berendeev.roma.foodscreen.presentation.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import org.berendeev.roma.foodscreen.R;
import org.berendeev.roma.foodscreen.domain.model.BonusAction;

import java.util.List;


/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
public class BonusActionAdapter extends RecyclerView.Adapter<BonusActionAdapter.ViewHolder>
{
	private final RequestManager glide;
	private final Context context;
	/**Static members**/

	/**Static getters and setters**/

	/**Static methods**/

	/**Members**/
	private List<BonusAction> items;

	/**Getters and setters**/
	@Override
	public int getItemCount() {
		int count = 0;
		if(items != null){
			count = items.size();
		}

		return count;
	}

	public void setDataList(List<BonusAction> items){
		this.items = items;
		notifyDataSetChanged();
	}

	/**Constructor**/
	public BonusActionAdapter(Context context, RequestManager glide) {
		this.context = context;
		this.glide = glide;

		setHasStableIds(true);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getBonusActionLongId();
	}

	/**Methods**/
	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int i) {
		BonusAction model = items.get(i);
		viewHolder.bindData(model);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.promo_item, parent, false);

		return new ViewHolder(v);
	}

	@Override
	public void onViewRecycled(ViewHolder holder) {
		super.onViewRecycled(holder);
		glide.clear(holder.imageView);
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView labelName;
		ImageView imageView;
		BonusAction bonusAction;
		String imageUrl = "";

		public ViewHolder(View itemView) {
			super(itemView);

			labelName = (TextView) itemView.findViewById(R.id.label_name);
			imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
		}

		public void bindData(BonusAction bonusAction) {
			this.bonusAction = bonusAction;
			labelName.setText(bonusAction.title());
			loadImage();
		}

		private void loadImage() {
			Drawable drawable;
			if (Build.VERSION.SDK_INT < 20){
				Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.blank_promo_vc);
				drawable = new BitmapDrawable(context.getResources(), bitmap);
			}else {
				drawable = ContextCompat.getDrawable(context, R.drawable.blank_promo_vc);
			}

			if (!imageUrl.equals(bonusAction.imageUrl())){
				RequestOptions options = new RequestOptions()
						.placeholder(drawable)
						.priority(Priority.HIGH);

				RequestBuilder<Drawable> requestBuilder = glide.load(bonusAction.imageUrl());

				requestBuilder
						.apply(options)
						.into(imageView);
			}


		}
	}
}
