package org.quietlip.guesswhatwho;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import org.quietlip.guesswhatwho.db.DatabaseContract;
import org.quietlip.guesswhatwho.db.GameDatabase;
import org.quietlip.guesswhatwho.frags.FirstFragment;
import org.quietlip.guesswhatwho.frags.SecondFragment;
import org.quietlip.guesswhatwho.frags.ThirdFragment;

public class MainActivity extends AppCompatActivity implements SecondFragment.OnCompletedListener
        , ThirdFragment.OnPlayAgainSelectedListener {
    private static final String TAG = "Main";
    private GameDatabase gameDatabase;
    private SQLiteDatabase sqLiteDatabase;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MainViewModelkt viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDB();
        viewModel = new ViewModelProvider(this).get(MainViewModelkt.class);
//        viewModel.passingContextBAD(this);
        FirstFragment fragment = new FirstFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame_layout, fragment);
        transaction.commit();
        viewModel.init(this);
        viewModel.getFragSelector().observe(this, fragName -> {
            switch (fragName) {
                case "second":
                    SecondFragment secondFragment = SecondFragment.newInstance("cars");
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_frame_layout, secondFragment);
                    transaction.addToBackStack("second");
                    transaction.commit();
                    break;
                case "third":
                    ThirdFragment thirdFragment = ThirdFragment.newInstance();
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_frame_layout, thirdFragment);
                    transaction.addToBackStack("third");
                    transaction.commit();
                    break;
            }
        });
    }

    public void openDB() throws SQLiteException {
        gameDatabase = new GameDatabase(this);
        sqLiteDatabase = gameDatabase.getWritableDatabase();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof SecondFragment) {
            SecondFragment secondFragment = (SecondFragment) fragment;
            secondFragment.setCompletedListener(this);
        } else if (fragment instanceof ThirdFragment) {
            ThirdFragment thirdFragment = (ThirdFragment) fragment;
            thirdFragment.setCompletedListener(this);
        }
    }


    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() != 0) {
            manager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void sendToDb(int win, int rounds, int gameResult) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GameEntry.COLUMN_NAME_WINS, win);
        values.put(DatabaseContract.GameEntry.COLUMN_NAME_ROUNDS, rounds);
        values.put(DatabaseContract.GameEntry.COLUMN_NAME_GUESS_RESULT, gameResult);
        sqLiteDatabase.insert(DatabaseContract.GameEntry.TABLE_NAME, null, values);
    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }


    @Override
    public void onPlayAgain(boolean playAgain) {
        if (playAgain) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
