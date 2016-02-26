package com.example.arlyn.hangman;

/*
 * Assignment #5
 * Team Flying Cats
 * Members: Jonathan Liu, Arlyn Rodriguez, Eugene Kolodenker, Dennis Cherchenko
 *
 */
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class LetterFragment extends Fragment {

    // Initialize buttons
    Button btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ;
    Button btnChosen;

    // Make true for debugging purposes to toast the letter of the button you click
    boolean toastLetter = false;
    String buttonText;

    public interface LetterFragmentListener {
        public void sendLetter(String letter);
    }
    private LetterFragmentListener LFL;

    public LetterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_letter, container, false);
        btnA = (Button) view.findViewById(R.id.btnA);
            btnA.setOnClickListener(onClickListener);
        btnB = (Button) view.findViewById(R.id.btnB);
            btnB.setOnClickListener(onClickListener);
        btnC = (Button) view.findViewById(R.id.btnC);
            btnC.setOnClickListener(onClickListener);
        btnD = (Button) view.findViewById(R.id.btnD);
            btnD.setOnClickListener(onClickListener);
        btnE = (Button) view.findViewById(R.id.btnE);
            btnE.setOnClickListener(onClickListener);
        btnF = (Button) view.findViewById(R.id.btnF);
            btnF.setOnClickListener(onClickListener);
        btnG = (Button) view.findViewById(R.id.btnG);
            btnG.setOnClickListener(onClickListener);
        btnH = (Button) view.findViewById(R.id.btnH);
            btnH.setOnClickListener(onClickListener);
        btnI = (Button) view.findViewById(R.id.btnI);
            btnI.setOnClickListener(onClickListener);
        btnJ = (Button) view.findViewById(R.id.btnJ);
            btnJ.setOnClickListener(onClickListener);
        btnK = (Button) view.findViewById(R.id.btnK);
            btnK.setOnClickListener(onClickListener);
        btnL = (Button) view.findViewById(R.id.btnL);
            btnL.setOnClickListener(onClickListener);
        btnM = (Button) view.findViewById(R.id.btnM);
            btnM.setOnClickListener(onClickListener);
        btnN = (Button) view.findViewById(R.id.btnN);
            btnN.setOnClickListener(onClickListener);
        btnO = (Button) view.findViewById(R.id.btnO);
            btnO.setOnClickListener(onClickListener);
        btnP = (Button) view.findViewById(R.id.btnP);
            btnP.setOnClickListener(onClickListener);
        btnQ = (Button) view.findViewById(R.id.btnQ);
            btnQ.setOnClickListener(onClickListener);
        btnR = (Button) view.findViewById(R.id.btnR);
            btnR.setOnClickListener(onClickListener);
        btnS = (Button) view.findViewById(R.id.btnS);
            btnS.setOnClickListener(onClickListener);
        btnT = (Button) view.findViewById(R.id.btnT);
            btnT.setOnClickListener(onClickListener);
        btnU = (Button) view.findViewById(R.id.btnU);
            btnU.setOnClickListener(onClickListener);
        btnV = (Button) view.findViewById(R.id.btnV);
            btnV.setOnClickListener(onClickListener);
        btnW = (Button) view.findViewById(R.id.btnW);
            btnW.setOnClickListener(onClickListener);
        btnX = (Button) view.findViewById(R.id.btnX);
            btnX.setOnClickListener(onClickListener);
        btnY = (Button) view.findViewById(R.id.btnY);
            btnY.setOnClickListener(onClickListener);
        btnZ = (Button) view.findViewById(R.id.btnZ);
            btnZ.setOnClickListener(onClickListener);
        return view;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnChosen = (Button) v;
            buttonText = btnChosen.getText().toString();
            btnChosen.setEnabled(false);  // On click, disable the button so the user can't click it again
            btnChosen.setBackgroundColor(Color.RED); // Changes color of button to show user they can't click it
            // Send the letter using the Letter Fragment Listener to the main activity to send to the Game Fragment
            LFL.sendLetter(buttonText);
            // toastLetter is a boolean that controls if you want to see a toast of what button you just pressed
            if (toastLetter) {Toast.makeText(getActivity().getBaseContext(), "We clicked on " + buttonText, Toast.LENGTH_SHORT).show();}
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LetterFragmentListener) {
            LFL = (LetterFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LetterFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LFL = null;
    }

}
