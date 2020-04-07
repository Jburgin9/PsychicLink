package org.quietlip.guesswhatwho.frags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.quietlip.guesswhatwho.R;

public class ThirdFragment extends Fragment {
    public static final String TAG = "third";
    private View view;
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
        gameAvgTv = view.findViewById(R.id.third_frag_avg_tv);
        gameResultTv = view.findViewById(R.id.third_frag_result_tv);
        playAgainBtn = view.findViewById(R.id.third_frag_play_again_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playAgainAction();
    }

    public void playAgainAction(){
        playAgainBtn.setOnClickListener(v -> selectedListener.onPlayAgain(true));
    }

    public interface OnPlayAgainSelectedListener{
        void onPlayAgain(boolean playAgain);
    }
}
