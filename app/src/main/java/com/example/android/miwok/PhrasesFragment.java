package com.example.android.miwok;


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
public class PhrasesFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

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

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listview = (ListView) rootview.findViewById(R.id.list);
        listview.setAdapter(adapter);

        //Create & setup AudioManager to request audio focus
        //This line gives you a reference to the AudioManager System Service
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        return rootview;
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
     * Called when the Fragment is no longer started..
     */
    @Override
    public void onStop() {
        super.onStop();
    }
}
