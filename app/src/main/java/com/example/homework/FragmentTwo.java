package com.example.homework;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

    Button button;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_two, parent, false);
        textView = rootView.findViewById(R.id.view_two);
        textView.setText(getArguments().getString("element"));
        textView.setTextColor(Color.parseColor((getArguments().getString("color"))));
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.button_two);
        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("TAG", "To first fragment ");
                ((MainActivity) getActivity()).putFragmentOne();
            }
        });
    }

}