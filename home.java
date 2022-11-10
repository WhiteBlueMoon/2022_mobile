package com.example.mobileteam1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {
    LinearLayout selectlayout;
    int selectchange;
    Button select1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        selectlayout = findViewById(R.id.selectLayout);
        select1 = (Button) findViewById(R.id.select_btn);
        registerForContextMenu(select1);

        ImageButton gohome_btn = (ImageButton) findViewById(R.id.imagebutton1);
        gohome_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton goremcommend_btn = (ImageButton) findViewById(R.id.imagebutton4);
        goremcommend_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), recommend.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();

        if (v == select1) {
            menuInflater.inflate(R.menu.menu, menu);
            selectchange = 1;
        }
    }

    @Override
        public boolean onContextItemSelected(@NonNull MenuItem item){
            if (selectchange == 1) {
                switch (item.getItemId()) {
                    case R.id.item1:

                        Intent intent0 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
                        startActivity(intent0);
                        return true;
                    case R.id.item2:

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
                        startActivity(intent);
                        return true;
                    case R.id.item3:

                        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
                        startActivity(intent1);
                        return true;
                }
                return false;
            }
            return false;
        }

}