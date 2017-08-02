package com.example.android.miwok;

/**
 * Created by Justin on 7/19/2017.
 * {@link Word} represents a vocabulary word that the
 * user wants to learn.
 * <p>
 * It contains a default translation and a Miwok translation
 * for that word.
 */

public class Word {
    private String englishWord;
    private String miwokWord;
    private final int NO_IMAGE_PROVIDED = -1;
    private final int NO_SOUND_PROVIDED = -1;
    private int wordAudioResourceId = NO_SOUND_PROVIDED;
    private int imageResourceId = NO_IMAGE_PROVIDED;

    //For Categories with only words and a sounds; No Image
    public Word(String englishWord, String miwokWord, int wordAudioResourceId) {
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.wordAudioResourceId = wordAudioResourceId;
    }

    //For categories with words, an image, and a sound
    public Word(String englishWord, String miwokWord, int imageResourceId, int wordAudioResourceId) {
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.imageResourceId = imageResourceId;
        this.wordAudioResourceId = wordAudioResourceId;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getWordAudioResourceId() {
        return wordAudioResourceId;
    }

    public boolean hasSound() {
        return wordAudioResourceId != NO_SOUND_PROVIDED;
    }
}
