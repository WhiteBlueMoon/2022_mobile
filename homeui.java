package com.example.teamproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class homeui extends Fragment {
    private View view;
    private Button enter,difficulty_set;
    private int difficulty, enter_Num,num;
    private String fileName;
    private FileOutputStream outputStream;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, false);
        view = inflater.inflate(R.layout.homeui, container, false);
        enter = view.findViewById(R.id.enter);
        difficulty_set = view.findViewById(R.id.difficulty);
        num=difficulty = 0;

        sharedPreferences = getActivity().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        num = sharedPreferences.getInt("num",0);
        enter_Num = sharedPreferences.getInt("enter_Num",1);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(difficulty > 0){
                    //아래 내용은 임시로 사용할것들
                    switch (difficulty){
                        case 3 :
                            Toast.makeText(getActivity().getApplicationContext(), "상", Toast.LENGTH_LONG ).show();
                            //enter_n.putInt("difficulty",difficulty);
                            //score.setArguments(enter_n);

                            //임시
                            filesave();


                            /*Intent intent = new Intent(getActivity(),유니티 받을거);
                            intent.putExtra("difficulty",3);*/
                            break;
                        case 2 :
                            Toast.makeText(getActivity().getApplicationContext(), "중", Toast.LENGTH_LONG ).show();
//                            score.setArguments(enter_n);

                            //임시
                            filesave();

                            /*Intent intent = new Intent(getActivity(),유니티 받을거);
                            intent.putExtra("difficulty",2);*/
                            break;
                        case 1 :
                            Toast.makeText(getActivity().getApplicationContext(), "하", Toast.LENGTH_LONG ).show();
//                            score.setArguments(enter_n);

                            //임시
                            filesave();

                            /*Intent intent = new Intent(getActivity(),유니티 받을거);
                            intent.putExtra("difficulty",1);*/
                            break;
                    }

                }else if(difficulty == 0){
                    Toast.makeText(getActivity().getApplicationContext(), "난이도 선택이 돼지않음", Toast.LENGTH_LONG ).show();
                }

                difficulty = 0;
            }
        });


        registerForContextMenu(difficulty_set);

        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        if(v==difficulty_set){
            menu.setHeaderTitle("난이도 선택");
            menuInflater.inflate(R.menu.difficulty,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.hard:
                difficulty = 3;

                return true;
            case R.id.normal:
                difficulty = 2;

                return true;
            case R.id.easy:
                difficulty = 1;

                return true;
        }

        return super.onContextItemSelected(item);
    }

    public void filesave(){

        //플레이 기록 저장, 최대 저장횟수는 현재 20으로 제한해둠

        try {
            fileName = "list"+Integer.toString(num)+".dat";
            outputStream = getActivity().openFileOutput(fileName,getContext().MODE_PRIVATE);

            //임시 저장방식
            String difficulty_str = null;
            switch (difficulty){
                case 1 :
                    difficulty_str = "하";
                    break;
                case 2 :
                    difficulty_str = "중";
                    break;
                case 3 :
                    difficulty_str = "상";
                    break;
            }

            String str ="입장 횟수 = " + Integer.toString(enter_Num) + " 난이도 = " + difficulty_str + " 틀린 횟수 = 3\n";
            outputStream.write(str.getBytes());
            outputStream.close();
            enter_Num++;
            num++;
            if(num > 20){
                num=0;
            }
            Toast.makeText(getActivity().getApplicationContext(), "저장됨", Toast.LENGTH_LONG ).show();

            //수치 일시저장 테스트할땐 //해놓자
            editor.putInt("num",num);
            editor.putInt("enter_Num",enter_Num);
            editor.apply();

        } catch (IOException e) {
            Toast.makeText(getActivity().getApplicationContext(), "오류", Toast.LENGTH_LONG ).show();
        }
    }
}
