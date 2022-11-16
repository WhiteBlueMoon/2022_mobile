package com.example.teamproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class main extends AppCompatActivity {

    FragmentManager manager;
    FragmentTransaction transaction;

    Button home_bt,score_bt,note_bt,propose_bt;
    homeui homeui;
    score score;
    note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainui);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //manager = getSupportFragmentManager();
        transaction  = getSupportFragmentManager().beginTransaction();

        //transaction = manager.beginTransaction();
        homeui = new homeui();

        //transaction.replace(R.id.frame,homeui).commit();
        transaction.replace(R.id.frame,homeui).commitAllowingStateLoss();
        // 아래는 전체 버튼이벤트 허나 현재 오류
        home_bt = (Button) findViewById(R.id.home_bt);
        home_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,homeui).commit();
            }
        });

        score_bt = (Button) findViewById(R.id.score_bt);
        score_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = new score();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,score).commit();
            }
        });

        note_bt = (Button) findViewById(R.id.note_bt);
        note_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              note = new note();
              transaction = getSupportFragmentManager().beginTransaction();
              transaction.replace(R.id.frame,note).commit();
            }
        });



    /*    propose_bt = (Button) findViewById(R.id.propose_bt);
        propose_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                propose = new propose();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,propose).commit();

            }
        });*/

    }
}
