package com.example.android.miwok;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    public NumbersFragment() {
        // Required empty public constructor
    }

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file. We store one instance in a variable so that we do not have to create a new instance
     * of it each time. Instead, we just pass the variable into the setOnCompleteionListener method below
     */
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    //Note that the layout for the Fragment will use the word_list XML layout
    //resource because it will be displaying a list of words.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /* TODO: Insert all the code from the NumberActivity’s onCreate()
          method after the setContentView method call */

        //Create the ArrayList of Word objects
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

            /*int index = 0;
            LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

            while(index < words.size()){
                TextView wordView = new TextView(this);
                wordView.setText(words.get(index));
                index++;
                rootView.addView(wordView);
            }

            for(index=0; index < words.size(); index ++){
                TextView wordView = new TextView(this);
                wordView.setText(words.get(index));
                rootView.addView(wordView);
            }*/

            /* Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            /   adapter knows how to create list items for each item in the list.
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
            */
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //Bind our WordAdapter to our ListView
        listView.setAdapter(adapter);

        //Create & setup AudioManager to request audio focus
        //This line gives you a reference to the AudioManager System Service
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Request audio focus so in order to play the audio file. The app needs to play a
                    short audio file, so we will request audio focus with a short amount of time
                    with AUDIOFOCUS_GAIN_TRANSIENT.
                */
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //Get the Word object at the given position the user has clicked on
                Word word = words.get(position);

                //Release the MediaPlayer if it is already configured. We do it just incase it's configured
                // to play a different audio file than the one we are ready to play
                releaseMediaPlayer();

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We now Have Audio Focus and can play the audio file

                    //Create and setup the MediaPlayer for the word that was clicked on
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getWordAudioResourceId());
                    // Start playing the audio file.
                    mediaPlayer.start();
                    //Setup a listener on the media player so that we can stop & release the MediaPlayer once the
                    //audio file is complete
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

        return rootView;
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {

            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            /*Regardless of whether or not we were granted audio focus, abandon it. This also
                unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
             */
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }
}
