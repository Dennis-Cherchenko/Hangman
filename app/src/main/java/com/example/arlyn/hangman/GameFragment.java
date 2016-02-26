package com.example.arlyn.hangman;

/*
 * Assignment #5
 * Team Flying Cats
 * Members: Jonathan Liu, Arlyn Rodriguez, Eugene Kolodenker, Dennis Cherchenko
 *
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameFragment extends Fragment {
    private ImageView imgMan;
    private TextView txtWord;
    int intIndex;
    private Random randomGen = new Random();

    // Sets an array of the words in the game
    private String[] arrayWords = {"APPLES","WINGS","HAMBURGER", "SUSHI", "SPAGHETTI", "FRENCH FRIES","PAD THAI"};
    private static String currentWord;
    private static String displayWord;
    private static int numMistakes= 0; //Count how many body parts of hangman have been displayed
    private static int gameOverMistakes = 7;  //if this many body parts are displayed, hangman is hung, game over


    public GameFragment() {
        // Required empty public constructor
    }

    public interface GameFragmentListener{
        public void sendHint(int intIndex);
    }

    private GameFragmentListener GFL;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GameFragmentListener) {
            GFL = (GameFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement GameFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intIndex = randomGen.nextInt(7); //send random int to use for array index to choose the word user has to guess
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        imgMan = (ImageView) view.findViewById(R.id.imgMan);
        txtWord = (TextView) view.findViewById(R.id.txtWord);

        GFL.sendHint(intIndex); //sendHint to MainActivity to HintFragment


        currentWord = arrayWords[intIndex]; // chooses the corresponding word to the intIndex in the arrayWords for the game
        displayWord = initializeDisplayWord(currentWord); // | _ _ _ _ |
        txtWord.setText(displayWord);

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        GFL = null;
    }

    // Replaces the letter if it is correct in the correct position of the displayWord
    private static void replaceLetter(int position, String letter){

        StringBuilder temp = new StringBuilder(displayWord);
        temp.setCharAt(position, letter.charAt(0));

        displayWord = temp.toString();

    }

    // Updates the display word when user chooses letter
    private boolean updateDisplayWord(String letter){

        boolean letterExistsInWord = false;

        for(int k = 0;  k < displayWord.length() - 1; k+=2) {

            String strCurrentWord = currentWord.charAt(k / 2) + "";
            if (strCurrentWord.equals(letter)) {
                replaceLetter(k, letter);
                letterExistsInWord = true;
            }

        }
        txtWord.setText(displayWord);
        Log.d("temp", currentWord + "||" + displayWord + "||");
        Log.d("temp", "^^^^^^^^");

        return letterExistsInWord;
    }

    // Initializes the display word from the currentWord to be all underscores as that is all blanks
    private static String initializeDisplayWord (String currentWord){

        String displayString = "";

        for(int k = 0;  k < currentWord.length(); k++){
            if((currentWord.charAt(k)+"").equals(" ")){
                displayString+= "  ";
            }else{
                displayString+= "_ ";
            }
        }
        return displayString;
    }

    // Checks if the user won by looking at the display word to see if there are any blanks, if none, they won
    private static boolean checkIfWon(String displayWord){
        for(int k = 0; k < displayWord.length(); k++){
            String strDisplayWord = displayWord.charAt(k) + "";
            if(strDisplayWord.equals("_")){
                return false;
            }
        }

        return true;
    }

    // Checks if the user has lost by comparing numMistakes counter to the gameOverMistakes
    private static boolean checkIfLost(){
        if(numMistakes == gameOverMistakes){
            return true;
        }else{
            return false;
        }
    }

    // Toast for winning game
    private void gameWon(){
        Toast.makeText(getActivity().getBaseContext(), "Congratulations, YOU WON!", Toast.LENGTH_LONG).show();
    }

    // Toast for losing game
    private void gameLost(){
        Toast.makeText(getActivity().getBaseContext(), "Sorry, you lost! :(", Toast.LENGTH_LONG).show();
    }

    // If neither win, nor lost, update number of mistakes as user did not win and add body part to hangman
    private void gameContinues(String letter){
        if(updateDisplayWord(letter) == false){
            numMistakes++;
            addBodyPartToHangman();
        }
    }

    //After you click a letter, check if you won, lost, or will still play on
    public void checkStep(String letter){
        gameContinues(letter);

        if(checkIfWon(displayWord) == true){
            gameWon();
        }else if(checkIfLost() == true){
            gameLost();
        }
    }

    // Update hangman image appropriately to the number of mistakes
    private void addBodyPartToHangman(){
        String temp = "stage" + numMistakes;
        System.out.println("WRONG: " + numMistakes);
        imgMan.setImageResource(getResources().getIdentifier(temp, "drawable", getActivity().getPackageName()));
    }


}
