package com.example.teamproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class main extends AppCompatActivity {

    FragmentTransaction transaction;
    Button home_bt,score_bt;
    homeui homeui;
    score score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainui);

        transaction  = getSupportFragmentManager().beginTransaction();
        homeui = new homeui();

        transaction.add(R.id.frame,homeui).commit();


        home_bt = (Button) findViewById(R.id.home_bt);
        home_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeui != null){
                    transaction.show(homeui).commit();
                }
                if(homeui != null){
                    transaction.hide(score).commit();
                }

            }
        });

        score_bt = (Button) findViewById(R.id.score_bt);
        score_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score == null){
                    score = new score();
                    transaction.add(R.id.frame,score).commit();
                }

                if(homeui != null){
                    transaction.hide(homeui).commit();
                }

                if(score != null){
                    transaction.show(score).commit();
                }
            }
        });


    }
}
