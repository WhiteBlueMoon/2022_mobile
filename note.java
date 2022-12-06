package com.example.teamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class note extends Fragment {

    private View view;
    private ListView Note_List;

    private ArrayList<ListItem> List = new ArrayList<ListItem>();
    private MyAdapter myAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(R.layout.score_bd, container, false);
        view = inflater.inflate(R.layout.note_bd, container, false);

        //listview 관련
        Note_List = (ListView) view.findViewById(R.id.Note_List);
        myAdapter = new MyAdapter(view.getContext(),List);



        //listview 화면 갱신
        Note_List.setAdapter(myAdapter);



        return view;
    }
}
