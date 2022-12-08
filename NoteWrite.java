package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NoteWrite extends AppCompatActivity {

    private TextView title;
    private EditText et_memo;
    private Button bt_w_back,bt_w_end;

    private Intent getIntent,putIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        title = (TextView) findViewById(R.id.title);
        et_memo = (EditText) findViewById(R.id.et_memo);
        bt_w_back = (Button) findViewById(R.id.bt_w_back);
        bt_w_end = (Button) findViewById(R.id.bt_w_end);

        et_memo.setEnabled(true);
        //받는 인텐트 선언 그리고 타이틀 설정 및 기존 메모 불러오기
        getIntent = getIntent();

        String str = new String(getIntent.getStringExtra("title").trim());
        title.setText(str);
        if (getIntent.getStringExtra("text") != null) {
            String string = new String(getIntent.getStringExtra("text"));
            et_memo.setText(string.trim());
        }

        bt_w_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_w_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putIntent = new Intent();
                String edit = getIntent.getStringExtra("title");
                String text = (et_memo.getText().toString()).trim();
                System.out.println(text);
                try {
                    //제목과 내용물을 파일로 저장함
                    FileOutputStream fileOutputStream = getApplication().openFileOutput(edit+".dat",MODE_PRIVATE);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    bufferedOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("열지 못했음");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("저장하지 못했음");
                }

                putIntent.putExtra("text",text);
                setResult(16,putIntent);
                finish();
            }
        });

    }
}
