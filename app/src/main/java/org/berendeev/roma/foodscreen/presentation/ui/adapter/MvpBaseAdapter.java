package org.berendeev.roma.foodscreen.presentation.ui.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.arellomobile.mvp.MvpDelegate;

public abstract class MvpBaseAdapter<E extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<E> {
	private MvpDelegate<? extends MvpBaseAdapter> mMvpDelegate;
	private MvpDelegate<?> mParentDelegate;
	private String mChildId;

	public MvpBaseAdapter(MvpDelegate<?> parentDelegate, String childId) {
		mParentDelegate = parentDelegate;
		mChildId = childId;

		getMvpDelegate().onCreate();
	}

	public MvpDelegate getMvpDelegate() {
		if (mMvpDelegate == null) {
			mMvpDelegate = new MvpDelegate<>(this);
			mMvpDelegate.setParentDelegate(mParentDelegate, mChildId);

		}
		return mMvpDelegate;
	}

	protected final View inflate(@LayoutRes int layoutId, ViewGroup parent){
		return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
	}
}
