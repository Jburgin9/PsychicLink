package org.quietlip.guesswhatwho.frags;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.quietlip.guesswhatwho.R;
import org.quietlip.guesswhatwho.utilis.GameConstants;

import java.util.Random;

/**
 * TODO: Fix pictures layout to fix to one size for all 4 images
 * TODO: CPU random choose image
 */

public class SecondFragment extends Fragment {
    public static final String TAG = "click";
    public static final String THEME_KEY = "theme";
    private View view;
    private String theme;
    private TextView displayTv;
    private ImageView choice1Iv, choice2Iv, choice3Iv, choice4Iv;
    private int cpuSelection, selection;
    private int roundCount, wins;
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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (getArguments() != null) {
            theme = getArguments().getString(THEME_KEY);
        }
        Random rando = new Random();
        cpuSelection = rando.nextInt(3);
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
        preferences = view.getContext().getSharedPreferences(GameConstants.GAME_DATA_PREF, Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(preferences.contains(GameConstants.ROUND_COUNT) && preferences.contains(GameConstants.SELECTION_WIN)){
            roundCount = preferences.getInt(GameConstants.ROUND_COUNT, 0);
            wins = preferences.getInt(GameConstants.SELECTION_WIN, 0);
        }
//        choice1Iv.setOnClickListener(this);
        choice1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: yup");
            }
        });

        displayTv.setText(theme);
        setThemeImages(theme);
        setImage1ClickAction();
        setImage2ClickAction();
        setImage3ClickAction();
        setImage4ClickAction();
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
                case "Music":
                    choice1Iv.setImageResource(R.drawable.music1);
                    choice2Iv.setImageResource(R.drawable.music2);
                    choice3Iv.setImageResource(R.drawable.music3);
                    choice4Iv.setImageResource(R.drawable.music4);
                    break;
                case "Pairs":
                    choice1Iv.setImageResource(R.drawable.pair1);
                    choice2Iv.setImageResource(R.drawable.pair2);
                    choice3Iv.setImageResource(R.drawable.pair3);
                    choice4Iv.setImageResource(R.drawable.pair4);
                    break;
            }
        }
    }

    public void setImage1ClickAction(){
        choice1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCount++;
//                if(cpuSelection == 0) cpuSelection = 1;
                int gameResult = -2;
                selection = 1;
                editor = preferences.edit();
                editor.putInt("userSelect", selection);
                editor.putInt("cpuSelect", cpuSelection);
                if (selection == cpuSelection) {
                    wins++;
                    gameResult = 1;
                    editor.putInt(GameConstants.SELECTION_WIN, wins);
                    } else {
                    gameResult = 0;
                    editor.putInt(GameConstants.ROUND_COUNT, roundCount);
                    }
                editor.apply();
                listener.onComplete();
                listener.sendToDb(wins, roundCount, gameResult);
                }
        });
    }

    public void setImage2ClickAction(){
        choice2Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCount++;
//                if(cpuSelection == 0) cpuSelection = 1;
                int gameResult = -2;
                selection = 2;
                editor = preferences.edit();
                editor.putInt("userSelect", selection);
                editor.putInt("cpuSelect", cpuSelection);
                if (selection == cpuSelection) {
                    wins++;
                    gameResult = 1;
                    editor.putInt(GameConstants.SELECTION_WIN, wins);
                } else {
                    gameResult = 0;
                    editor.putInt(GameConstants.ROUND_COUNT, roundCount);
                }
                editor.apply();
                listener.onComplete();
                listener.sendToDb(wins, roundCount, gameResult);
            }
        });
    }

    public void setImage3ClickAction(){
        choice3Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCount++;
//                if(cpuSelection == 0) cpuSelection = 1;
                int gameResult = -2;
                selection = 3;
                editor = preferences.edit();
                editor.putInt("userSelect", selection);
                editor.putInt("cpuSelect", cpuSelection);
                if (selection == cpuSelection) {
                    wins++;
                    gameResult = 1;
                    editor.putInt(GameConstants.SELECTION_WIN, wins);
                } else {
                    gameResult = 0;
                    editor.putInt(GameConstants.ROUND_COUNT, roundCount);
                }
                editor.apply();
                listener.onComplete();
                listener.sendToDb(wins, roundCount, gameResult);
            }
        });
    }

    public void setImage4ClickAction(){
        choice4Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCount++;
//                if(cpuSelection == 0) cpuSelection = 1;
                int gameResult = -2;
                selection = 4;
                editor = preferences.edit();
                editor.putInt("userSelect", selection);
                editor.putInt("cpuSelect", cpuSelection);
                if (selection == cpuSelection) {
                    wins++;
                    gameResult = 1;
                    editor.putInt(GameConstants.SELECTION_WIN, wins);
                } else {
                    gameResult = 0;
                    editor.putInt(GameConstants.ROUND_COUNT, roundCount);
                }
                editor.apply();
                listener.onComplete();
                listener.sendToDb(wins, roundCount, gameResult);
            }
        });
    }


    public interface OnCompletedListener {
        void onComplete();
        void sendToDb(int win, int rounds, int gameResult);
    }

}
