package com.example.mobileteam1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View drawerView;
    Button btnplay,btnpause,btnstop;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnplay = findViewById(R.id.play);
        btnpause = findViewById(R.id.pause);
        btnstop = findViewById(R.id.stop);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player == null)
                {
                    player = MediaPlayer.create(MainActivity.this, R.raw.sadday);
                }
                player.start();
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player !=null)
                {
                    player.pause();
                }
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player != null)
                {
                    player.release();
                    player = null;
                }
            }
        });

        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView =(View)findViewById(R.id.drawer);

        ImageButton btn_open =(ImageButton)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        Button btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        Button gamestart1_btn = (Button) findViewById(R.id.gamestart_btn);
        gamestart1_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
            }
        });

        Button gohelp = findViewById(R.id.help);
        gohelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), help.class);
                startActivity(intent);
            }
        });

    }
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}