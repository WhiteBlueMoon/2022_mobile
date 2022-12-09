package com.example.teamproject;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class score extends Fragment {

    private View view;
    private ListView listView;
    private Button b;
    private ArrayList<ListItem> arrayList = new ArrayList<ListItem>();
    private MyAdapter myAdapter;
    private byte[] text = new byte[1024];
    int a;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(R.layout.score_bd, container, false);
        view = inflater.inflate(R.layout.score_bd, container, false);

        //목적은 테스트용 버튼이었으나 갱신용으로 바뀜
        b = view.findViewById(R.id.test);


        listView = (ListView) view.findViewById(R.id.score_bd_S);

        a=0;
        FileRead();

        myAdapter = new MyAdapter(view.getContext(),arrayList);
        listView.setAdapter(myAdapter);


        //listview 갱신 (점수 현황판)
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                FileRead();
                listView.setAdapter(myAdapter);
            }
        });



        //difficulty1_txt.setOnClickListener();

        return view;
    }


    public void FileRead(){

        FileInputStream infs;
        for (int n = 0 ; n < 20 ; n++) {
            try {
                infs = getActivity().openFileInput("list" + Integer.toString(n) + ".dat");

                infs.read(text);
                infs.close();
                String str = "저장 " + Integer.toString(n);
                String ViewText = (new String(text).trim());
                Arrays.fill(text, (byte) 0);

                arrayList.add(new ListItem(str, ViewText));
                //return 1;
            } catch (IOException e) {
                //return 0;
                break;
            }

        }
    }
}
