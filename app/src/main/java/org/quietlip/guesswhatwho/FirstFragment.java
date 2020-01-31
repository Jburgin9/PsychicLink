package org.quietlip.guesswhatwho;

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

public class FirstFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private GameDatabase gameDb;
    private View view;
    private Spinner spinner;
    private OnThemeSelectedListener selectedListener;

    public void setThemeListener(OnThemeSelectedListener listener){
        selectedListener = listener;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        gameDb = new GameDatabase(view.getContext());

//        SQLiteDatabase db = gameDb.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(DatabaseContract.GameEntry.COLUMN_NAME_ROUND, "1");
//        db.insert(DatabaseContract.GameEntry.TABLE_NAME, null, values);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_fragment_layout, container, false);
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
    }


    @Override
    public void onDetach() {
        super.onDetach();
        selectedListener = null;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!parent.getItemAtPosition(position).equals("-")){
            selectedListener.onSelectionMade(parent.getItemAtPosition(position).toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public interface OnThemeSelectedListener {
        void onSelectionMade(String theme);
    }



}
