package com.example.teamproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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

public class homeui extends Fragment {
    View view,popup;
    Button enter,difficulty_set;
    int difficulty,enter_f;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, false);
        //현재 화면 띄우는 기능만하는중
        view = inflater.inflate(R.layout.homeui, container, false);
        enter = view.findViewById(R.id.enter);
        difficulty_set = view.findViewById(R.id.difficulty);
        difficulty = 0;

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(difficulty > 0){
                    //아래 내용은 임시로 사용할것들
                    switch (difficulty){
                        case 3 :
                            enter_f = 1;
                            Toast.makeText(getActivity().getApplicationContext(), "상", Toast.LENGTH_LONG ).show();

                            /*Intent intent = new Intent(getActivity(),유니티 받을거);
                            intent.putExtra("difficulty",3);*/
                            break;
                        case 2 :
                            enter_f = 1;
                            Toast.makeText(getActivity().getApplicationContext(), "중", Toast.LENGTH_LONG ).show();

                            /*Intent intent = new Intent(getActivity(),유니티 받을거);
                            intent.putExtra("difficulty",2);*/
                            break;
                        case 1 :
                            enter_f = 1;
                            Toast.makeText(getActivity().getApplicationContext(), "하", Toast.LENGTH_LONG ).show();

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
}
