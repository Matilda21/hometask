package com.example.homework;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;

public class fragment_one extends Fragment implements RecyclerViewAdapter.ItemClickListener {
    Button button;
    RecyclerViewAdapter adapter;
    int numberOfColumns;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, parent, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = getArguments().getStringArrayList("data");
        adapter = new RecyclerViewAdapter(getActivity(), data);
        RecyclerView recyclerView = view.findViewById(R.id.rvNumbers);
        button = view.findViewById(R.id.btn_add);

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) { //Adding element by button click
                adapter.AddItem();
                adapter.notifyDataSetChanged();
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
             numberOfColumns = 3;
        else
            numberOfColumns = 4;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position % 2 == 0)
            ((MainActivity) getActivity()).putFragmentTwo(adapter.getItem(position), adapter.redColor);
        else
            ((MainActivity) getActivity()).putFragmentTwo(adapter.getItem(position), adapter.blueColor);
    }
}