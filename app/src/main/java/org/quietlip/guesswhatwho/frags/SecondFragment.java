package org.quietlip.guesswhatwho.frags;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.quietlip.guesswhatwho.R;
import org.quietlip.guesswhatwho.utilis.GameConstants;

import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * TODO: Fix pictures layout to fix to one size for all 4 images
 * TODO: CPU random choose image
 */

public class SecondFragment extends Fragment {
    public static final String THEME_KEY = "theme";
    private View view;
    private String theme;
    private TextView displayTv;
    private ImageView choice1Iv, choice2Iv, choice3Iv, choice4Iv;
    private EditText userGuessEt;
    private Button submitGuessBtn;
    private int cpuSelection;
    private int roundCount, wins = 0;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private OnCompletedListener listener;

    public void setCompletedListener(OnCompletedListener listener) {this.listener = listener;}


    public static SecondFragment newInstance(String theme) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(THEME_KEY, theme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            theme = getArguments().getString(THEME_KEY);
        }
        Random rando = new Random();
        cpuSelection = rando.nextInt(3);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_fragment_layout, container, false);
        displayTv = view.findViewById(R.id.display_frag_header_tv);
        choice1Iv = view.findViewById(R.id.display_image_1);
        choice2Iv = view.findViewById(R.id.display_image_2);
        choice3Iv = view.findViewById(R.id.display_image_3);
        choice4Iv = view.findViewById(R.id.display_image_4);
        userGuessEt = view.findViewById(R.id.user_guess_et);
        submitGuessBtn = view.findViewById(R.id.submit_guess_btn);
        preferences = view.getContext().getSharedPreferences(GameConstants.GAME_DATA_PREF, Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayTv.setText(theme);
        setThemeImages(theme);
        submitGuessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    private void setThemeImages(String theme) {
        if (theme != null) {
            switch (theme) {
                case "Cars":
                    //TODO: Get Drawable resource
                    choice1Iv.setImageResource(R.drawable.car1);
                    choice2Iv.setImageResource(R.drawable.car2);
                    choice3Iv.setImageResource(R.drawable.car3);
                    choice4Iv.setImageResource(R.drawable.car4);
                    break;
                case "Sports":
//                    choice1Iv.setImageResource(R.drawable.car1);
                    break;
            }
        }
    }

    private void selectImage() {
        int selection = 0;
       editor = preferences.edit();
        if (!userGuessEt.getText().toString().equals("")) {
            roundCount++;
            selection = Integer.parseInt(userGuessEt.getText().toString());
            editor.putInt("userSelect", selection);
            editor.putInt("cpuSelect", cpuSelection);
            Log.d(TAG, "selectImage: " + cpuSelection);
            if (selection == cpuSelection) {
                Toast.makeText(view.getContext(), "Correct", Toast.LENGTH_SHORT).show();
                editor.putInt(GameConstants.SELECTION_WIN, wins);
                wins++;
            } else {
                Toast.makeText(view.getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                editor.putInt(GameConstants.SELECTION_LOSS, roundCount);
            }
        }
        editor.apply();
        listener.onComplete();
        listener.sendToDb(wins, roundCount, selection, cpuSelection);
    }

    public interface OnCompletedListener {
        void onComplete();
        void sendToDb(int win, int rounds, int userGuess, int cpuGuess);
    }

}
