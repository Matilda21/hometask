package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    fragment_one fragment_one = new fragment_one();
    fragment_two fragment_two = new fragment_two();
    private ArrayList<String> data = new ArrayList<>();

    private int current_fragment = 1; //current fragment
    private String current_element;
    private String current_color;
    private Bundle bundle = new Bundle();

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
       // Log.i("TAG", "OnSaveInstamceStateMain");
        //Log.i("TAG", String.valueOf(data.size()));

        outState.putStringArrayList("elements", data);         //
        outState.putInt("current_fragment", current_fragment);  //
        outState.putString("current_element", current_element); //
        outState.putString("current_color", current_color);  //
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            Log.i("TAG", "Restoring data in main");
            data = savedInstanceState.getStringArrayList("elements");
            current_fragment = savedInstanceState.getInt("current_fragment");
            current_element = savedInstanceState.getString("current_element");
            current_color = savedInstanceState.getString("current_color");

            if (current_fragment == 1){
                putFragmentOne();
            }
            else if (current_fragment == 2){

                putFragmentTwo(current_element, current_color);
            }
        }

        else {
            for (int i = 0; i < 100; i++) {
                data.add(String.valueOf(i + 1));
            }
            putFragmentOne();

        }
    }

    public void putFragmentTwo(String element, String color){
        bundle.clear();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.i("LOG", "NEW_OBG");

        bundle.putString("element", element);
        bundle.putString("color", color);
        fragment_two.setArguments(bundle);
        transaction.replace(R.id.container, fragment_two);

        transaction.commit();
        current_fragment = 2;
        current_element = element;
        current_color = color;
    }

    public void putFragmentOne(){
        bundle.clear();
        bundle.putStringArrayList("data", data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.i("LOG", "NEW_OBG");

        fragment_one.setArguments(bundle);
        transaction.replace(R.id.container, fragment_one);

        transaction.commit();
        current_fragment = 1;
    }



}