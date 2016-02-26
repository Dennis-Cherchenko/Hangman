package com.example.arlyn.hangman;

/*
 * Assignment #5
 * Team Flying Cats
 * Members: Jonathan Liu, Arlyn Rodriguez, Eugene Kolodenker, Dennis Cherchenko
 *
 */
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements GameFragment.GameFragmentListener, LetterFragment.LetterFragmentListener {

    // The function that promotes the interaction between the Game Fragment and Letter Fragment to send the letter chosen to the game as a string
    public void sendLetter(String letter) {
        GameFragment gameFragment = (GameFragment) getSupportFragmentManager().findFragmentById(R.id.gameFragment);
        gameFragment.checkStep(letter);
    }

    // The function that promotes the interaction between the Hint Fragment and Game Fragment to display the corresponding hint to the word in the game
    public void sendHint(int intIndex) {
        HintFragment hintFragment = (HintFragment) getSupportFragmentManager().findFragmentById(R.id.hintFragment);
        if (hintFragment != null) {
            hintFragment.setHint(intIndex);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
