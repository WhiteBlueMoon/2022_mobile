package com.example.mobileteam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class chapter1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("chapter 1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter1);

        ImageButton gochapter11pic = (ImageButton) findViewById(R.id.chapter11pic);
        gochapter11pic.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://soltudy.tistory.com/1353"));
                startActivity(intent1);
            }
        });
        ImageButton gochapter12pic = (ImageButton) findViewById(R.id.chapter12pic);
        gochapter12pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yes24.com/Product/goods/12580964"));
                startActivity(intent2);
            }
        });
    }

    public void onButton13Clicked(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);

        Toast toast = new Toast(this);
        text.setText("•하늘색 사각형의 개수와 흰색 사각형의 개수를 확인하세요\n" +
                "•분모가 같다면 분자가 큰 분수가 더 큰 분수입니다");
        toast.setGravity(Gravity.CENTER, 0, -25);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void onButton12Clicked(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);

        Toast toast = new Toast(this);
        text.setText("•칠판에 써진 분수의 규칙을 찾으세요");
        toast.setGravity(Gravity.CENTER, 0, -25);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    public void onButton11Clicked(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);

        Toast toast = new Toast(this);
        text.setText("•자연수와 분모를 곱하고 분자를 더하면 됩니다");
        toast.setGravity(Gravity.CENTER, 0, -25);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}