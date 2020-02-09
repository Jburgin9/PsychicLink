package org.quietlip.guesswhatwho.frags;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.quietlip.guesswhatwho.R;
import org.quietlip.guesswhatwho.utilis.GameConstants;

public class FirstFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private View view;
    private Spinner spinner;
    private OnThemeSelectedListener selectedListener;
    private SharedPreferences preferences;

    public void setThemeListener(OnThemeSelectedListener listener){
        selectedListener = listener;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_fragment_layout, container, false);
        preferences = view.getContext().getSharedPreferences(GameConstants.GAME_DATA_PREF, Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.first_fragment_spinner);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.spinner_selection, android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
        if(preferences.contains(GameConstants.SELECTION_WIN) || preferences.contains(GameConstants.SELECTION_LOSS)){
            int result = preferences.getInt(GameConstants.SELECTION_WIN, 0);
            int rounds = preferences.getInt(GameConstants.SELECTION_LOSS, -1);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        selectedListener = null;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selection = "";
        if(!parent.getItemAtPosition(position).equals("-")){
            selection = parent.getItemAtPosition(position).toString();
            selectedListener.onSelectionMade(selection);
            parent.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public interface OnThemeSelectedListener {
        void onSelectionMade(String theme);
    }



}
