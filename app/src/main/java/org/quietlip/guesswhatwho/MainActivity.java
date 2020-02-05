package org.quietlip.guesswhatwho;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.quietlip.guesswhatwho.frags.FirstFragment;
import org.quietlip.guesswhatwho.frags.SecondFragment;
import org.quietlip.guesswhatwho.frags.ThirdFragment;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnThemeSelectedListener,
SecondFragment.OnCompletedListener {
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
        } else if (fragment instanceof SecondFragment){
            SecondFragment secondFragment = (SecondFragment) fragment;
            secondFragment.setCompletedListener(this);
        } else {
            ThirdFragment thirdFragment = (ThirdFragment) fragment;
        }
    }

    @Override
    public void onSelectionMade(String theme) {
        if(!theme.equals("-")) {
            SecondFragment secondFragment = SecondFragment.newInstance(theme);
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

    @Override
    public void onComplete() {
        ThirdFragment fragment = ThirdFragment.newInstance();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame_layout, fragment);
        transaction.addToBackStack("third");
        transaction.commit();
    }
}
