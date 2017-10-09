package org.berendeev.roma.foodscreen.presentation.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import org.berendeev.roma.foodscreen.presentation.ui.fragment.DummyViewFragment;

import java.util.List;


/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
public class FragmentManagmentFragmentAdapter extends FragmentStatePagerAdapter
{
	/**Static members**/

	/**Static getters and setters**/

	/**Static methods**/

	/**Members**/
	private Context context;
	private List<Integer> objects;
	private List<Integer> oldObjects;

	List<String> titles;
	private int currentItem;

	/**Getters and setters**/
	@Override
	public Fragment getItem(int position) {
		Log.d("","new fragment with position " + position);
		return DummyViewFragment.getInstance(String.valueOf(objects.get(position)));
	}

	@Override
	public int getItemPosition(Object object) {
		int position = getPosition(object);
		return position;
	}

	private int getPosition(Object object){
		String objectMessage = ((DummyViewFragment) object).getMessage();
		Integer intObject = Integer.parseInt(objectMessage);
		int index = objects.indexOf(intObject);
		int oldIndex = oldObjects.indexOf(intObject);
		if (index == oldIndex){
			return POSITION_UNCHANGED;
		}else {
			return POSITION_NONE;
		}
	}

	@Override
	public int getCount() {
		return titles.size();
	}

	@Override
	public CharSequence getPageTitle(int position){
		return titles.get(position);
	}

	/**Constructor**/
	public FragmentManagmentFragmentAdapter(FragmentManager fm, Context context, List<String> titles, List<Integer> objects) {
		super(fm);
		this.context = context;
		this.titles = titles;
		this.objects = objects;
	}

	public void update(int currentItem, List<Integer> objects, List<String> titles){
		this.currentItem = currentItem;
		this.oldObjects = this.objects;
		this.objects = objects;
		this.titles = titles;
		notifyDataSetChanged();
	}

	/**Methods**/
}
