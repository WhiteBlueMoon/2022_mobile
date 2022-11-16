package com.example.teamproject;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class score extends Fragment {

    View view;
    TextView difficulty1_txt;
    int difficulty = 0;
    Adapter adapter;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(R.layout.score_bd, container, false);
        //현재 화면 띄우는 기능만하는중
        view = inflater.inflate(R.layout.score_bd, container, false);
        difficulty1_txt = (TextView) view.findViewById(R.id.difficulty1_txt);
        registerForContextMenu(difficulty1_txt);  //임시
        listView = (ListView) view.findViewById(R.id.score_bd);


        //difficulty1_txt.setOnClickListener();

        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        if(v==difficulty1_txt){
            menu.setHeaderTitle("난이도 선택");
            menuInflater.inflate(R.menu.difficulty,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.hard:
                difficulty1_txt.setText("난이도 상");
                difficulty = 3;
                return true;
            case R.id.normal:
                difficulty1_txt.setText("난이도 중");
                difficulty = 2;
                return true;
            case R.id.easy:
                difficulty1_txt.setText("난이도 하");
                difficulty = 1;
                return true;
        }

        return super.onContextItemSelected(item);
    }
/*
    public class setadapter extends BaseAdapter{

        public setadapter() {
            super();
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }


    }

 */
}
