package org.quietlip.guesswhatwho.frags;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.quietlip.guesswhatwho.R;
import org.quietlip.guesswhatwho.db.DatabaseContract;
import org.quietlip.guesswhatwho.db.GameDatabase;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment {
    public static final String TAG = "third";
    private View view;
    private SQLiteDatabase sqLiteDatabase;
    private GameDatabase gameDatabase;
    private TextView gameAvgTv, gameResultTv;
    private Button playAgainBtn;
    private OnPlayAgainSelectedListener selectedListener;

    public void setCompletedListener(OnPlayAgainSelectedListener listener) {selectedListener = listener;}

    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third_fragment_layout, container, false);
        gameDatabase = new GameDatabase(view.getContext());
        sqLiteDatabase = gameDatabase.getReadableDatabase();
        gameAvgTv = view.findViewById(R.id.third_frag_avg_tv);
        gameResultTv = view.findViewById(R.id.third_frag_result_tv);
        playAgainBtn = view.findViewById(R.id.third_frag_play_again_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] projection = {
//                BaseColumns._ID,
                DatabaseContract.GameEntry.COLUMN_NAME_WINS,
                DatabaseContract.GameEntry.COLUMN_NAME_ROUNDS,
                DatabaseContract.GameEntry.COLUMN_NAME_GUESS_RESULT
        };

        Cursor cursor = sqLiteDatabase.query(DatabaseContract.GameEntry.TABLE_NAME,
                projection, null, null, null, null, null);

        List<Integer> winsList = new ArrayList<>();
        List<Integer> roundsList = new ArrayList<>();
        List<Integer> resultsList = new ArrayList<>();
        int count = 0;
        while(cursor.moveToNext()){
            winsList.add(cursor.getInt(cursor.getColumnIndex(DatabaseContract.GameEntry.COLUMN_NAME_WINS)));
            roundsList.add(cursor.getInt(cursor.getColumnIndex(DatabaseContract.GameEntry.COLUMN_NAME_ROUNDS)));
            resultsList.add(cursor.getInt(cursor.getColumnIndex(DatabaseContract.GameEntry.COLUMN_NAME_GUESS_RESULT)));
            Log.d(TAG, "onViewCreated: " + winsList.get(count));
            if(resultsList.get(count) == 1) {
                gameResultTv.setText("You guessed correctly");
            } else {
                gameResultTv.setText("You guessed incorrectly");
            }
            gameAvgTv.setText(winsList.get(count).toString() + "/" + roundsList.get(count).toString());
            count++;
        }

        cursor.close();

        playAgainAction();
    }

    public void playAgainAction(){
        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedListener.onPlayAgain(true);
            }
        });
    }

    public interface OnPlayAgainSelectedListener{
        void onPlayAgain(boolean playAgain);
    }
}
