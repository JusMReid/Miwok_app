package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Justin on 7/19/2017.
 * The WordAdapter is responsible for loading the right information from the
 * word object to the list_item layout. This includes loading the right image
 * into the ImageView
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundColorResourceID;

    public WordAdapter(Activity context, ArrayList<Word> words, int backgroundColorResourceID){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.backgroundColorResourceID = backgroundColorResourceID;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     * This method gets called when the list view is trying to display a
     * list of items at a given position
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        /*Inflating the list item view means that we create a new list_item
        * layout from the given xml resource (In this case, R.layout.list_item)
        * and we then store it in the listItemView variable
        *
        * */
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokTextView);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getMiwokWord());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.englishTextView);
        // Get the version number from the current Word object and
        // set this text on the number TextView
        englishTextView.setText(currentWord.getEnglishWord());


        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current Word object and
        // set the image to iconView ONLY if that word has an image
        // associated with it
        if(currentWord.hasImage()){
            iconImageView.setImageResource(currentWord.getImageResourceId());
            iconImageView.setVisibility(View.VISIBLE);
        }else {
            iconImageView.setVisibility(View.GONE);
        }

        //set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.textViewsLayout);
        //Find the color that the resource ID maps //
        int color = ContextCompat.getColor(getContext(), backgroundColorResourceID);
        //Set the background color of the text container view
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
