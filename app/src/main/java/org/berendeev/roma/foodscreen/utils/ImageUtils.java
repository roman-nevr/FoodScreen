package org.berendeev.roma.foodscreen.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

/**
 * Android Studio
 *
 * @author Roman Berendeev (roman.berendeev@rentateam.ru)
 */
public class ImageUtils{
	public static Drawable getVectorDrawable(Context context, @DrawableRes int res){
		Drawable drawable;
		if (Build.VERSION.SDK_INT < 20){
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);
			drawable = new BitmapDrawable(context.getResources(), bitmap);
		}else {
			drawable = ContextCompat.getDrawable(context, res);
		}
		return drawable;
	}

	public static int dpToPixels(int dp, float density){
		return (int) (dp * density);
	}
}
