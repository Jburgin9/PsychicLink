package org.quietlip.guesswhatwho;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    public static final String THEME_KEY = "theme";
    private View view;
    private String theme;
    private TextView displayTv;

    public static SecondFragment newInstance(String theme){
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(THEME_KEY, theme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            theme = getArguments().getString(THEME_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_fragment_layout, container, false);
        displayTv = view.findViewById(R.id.display_frag_2nd);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayTv.setText(theme);
    }

}
