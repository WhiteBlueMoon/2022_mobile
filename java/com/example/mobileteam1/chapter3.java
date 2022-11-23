package com.example.mobileteam1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class chapter3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("chapter 3");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3);
    }
    public void onButton33Clicked(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);

        Toast toast = new Toast(this);
        text.setText("chapter3 하");
        toast.setGravity(Gravity.CENTER, 0, -25);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void onButton32Clicked(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);

        Toast toast = new Toast(this);
        text.setText("chapter3 중");
        toast.setGravity(Gravity.CENTER, 0, -25);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    public void onButton31Clicked(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(
                R.layout.toastborder,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);

        Toast toast = new Toast(this);
        text.setText("chapter3 상");
        toast.setGravity(Gravity.CENTER, 0, -25);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}