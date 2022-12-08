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

    private FragmentManager manager;

    private Button home_bt,score_bt,note_bt,propose_bt;
    private homeui homeui;
    private score score;
    private note note;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainui);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        manager = getSupportFragmentManager();

        homeui = new homeui();
        manager.beginTransaction().add(R.id.frame,homeui).commit();

        //아래의 모든 버튼은 화면 띄워주는용도로만 사용함 그 이외 기능 X

        home_bt = (Button) findViewById(R.id.home_bt);
        home_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(homeui == null) {
                    homeui = new homeui();
                    manager.beginTransaction().add(R.id.frame,homeui).commit();       //게임시작 플래그먼트
                }

                if(homeui != null) manager.beginTransaction().show(homeui).commit();  //게임시작
                if(score != null) manager.beginTransaction().hide(score).commit();    //점수
                if(note != null) manager.beginTransaction().hide(note).commit();      //오답
            }
        });

        score_bt = (Button) findViewById(R.id.score_bt);
        score_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(score == null) {
                    score = new score();
                    manager.beginTransaction().add(R.id.frame,score).commit();        //점수 플래그먼트
                }

                if(homeui != null) manager.beginTransaction().hide(homeui).commit();  //게임시작
                if(score != null) {
                    manager.beginTransaction().show(score).commit();
                }    //점수
                if(note != null) manager.beginTransaction().hide(note).commit();      //오답
            }
        });

        note_bt = (Button) findViewById(R.id.note_bt);
        note_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(note == null) {
                    note = new note();
                    manager.beginTransaction().add(R.id.frame,note).commit();          //오답
                }

                if(homeui != null) manager.beginTransaction().hide(homeui).commit();  //게임시작
                if(score != null) manager.beginTransaction().hide(score).commit();    //점수
                if(note != null) manager.beginTransaction().show(note).commit();      //오답
            }
        });


        //다른방식의 프래그먼트

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
