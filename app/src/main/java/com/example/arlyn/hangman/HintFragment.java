package com.example.arlyn.hangman;

/*
 * Assignment #5
 * Team Flying Cats
 * Members: Jonathan Liu, Arlyn Rodriguez, Eugene Kolodenker, Dennis Cherchenko
 *
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HintFragment extends Fragment {


    TextView lblHint;
    String[] arrayHint = {"KEEPS THE DR. AWAY", "FLY AWAY", "HOLD THE CHEESE", "ISN'T THIS BAIT?", "I LOVE CARBS!", "VIVE LA FRANCE", "FISH SAUCE MAKES IT GOOD"};

    public HintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hint, container, false);
        lblHint = (TextView) view.findViewById(R.id.lblHint);
        return view;
    }

    // Sets the hint using the random integer generated from game fragment and finds the corresponding hint in the arrayHint and sets it to the TextView
    public void setHint(int intIndex) {
        if (lblHint != null) {
            lblHint.setText(arrayHint[intIndex]);
        }
    }
}
