package com.example.mobileteam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko.khanacademy.org/math/arithmetic/arith-review-add-subtract/arith-review-basic-add-subtract/v/basic-addition"));
                startActivity(intent1);
            }
        });
        ImageButton gochapter12pic = (ImageButton) findViewById(R.id.chapter12pic);
        gochapter12pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coupang.com/np/search?component=&q=%EC%88%98%ED%95%99+%EB%8D%A7%EC%85%88&channel=user"));
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
        text.setText("4 + 10 = 14\n" + "10 - 4 = 6");
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
        text.setText("40 + 100 = 140\n" + "100 - 36 = 64");
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
        text.setText("440 + 1520 = 1960\n"+ "1520 - 657 = 863");
        toast.setGravity(Gravity.CENTER, 0, -25);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}