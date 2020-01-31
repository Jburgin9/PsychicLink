package org.quietlip.guesswhatwho.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.quietlip.guesswhatwho.FirstFragment;
import org.quietlip.guesswhatwho.R;
import org.quietlip.guesswhatwho.SecondFragment;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnThemeSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment fragment = new FirstFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if(fragment instanceof FirstFragment){
            FirstFragment firstFragment = (FirstFragment) fragment;
            firstFragment.setThemeListener(this);
        }
    }

    @Override
    public void onSelectionMade(String theme) {
        if(!theme.equals("-")) {
            SecondFragment secondFragment = SecondFragment.getInstance(theme);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_frame_layout, secondFragment);
            transaction.addToBackStack("second");
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if(manager.getBackStackEntryCount() != 0){
            manager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
