package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Justin on 7/30/2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context context;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new NumbersFragment();
        }else if(position == 1){
            return new ColorsFragment();
        }else if(position == 2){
            return new FamilyFragment();
        }else{
            return new PhrasesFragment();
        }
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        if(position == 0){
            return context.getString(R.string.category_numbers);
        }else if(position == 1){
            return context.getString(R.string.category_colors);
        }else if(position == 2){
            return context.getString(R.string.category_family);
        }else{
            return context.getString(R.string.category_phrases);
        }
    }

    /**
     * Return a unique identifier for the item at the given position.
     * <p>
     * <p>The default implementation returns the given position.
     * Subclasses should override this method if the positions of items can change.</p>
     *
     * @param position Position within this adapter
     * @return Unique identifier for the item at position
     */

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 4;
    }
}
