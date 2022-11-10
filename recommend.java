package com.example.mobileteam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class recommend extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ImageButton gochapter1 = findViewById(R.id.chapter1_btn);
        gochapter1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), chapter1.class);
                startActivity(intent);
            }
        });
        ImageButton gochapter2 = findViewById(R.id.chapter2_btn);
        gochapter2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), chapter2.class);
                startActivity(intent);
            }
        });
        ImageButton gochapter3 = findViewById(R.id.chapter3_btn);
        gochapter3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), chapter3.class);
                startActivity(intent);
            }
        });
        ImageButton gochapter4 = findViewById(R.id.chapter4_btn);
        gochapter4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), chapter4.class);
                startActivity(intent);
            }
        });
        ImageButton gochapter5 = findViewById(R.id.chapter5_btn);
        gochapter5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), chapter5.class);
                startActivity(intent);
            }
        });

    }


}