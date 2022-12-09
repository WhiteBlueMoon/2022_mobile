package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater layoutInflater = null;
    ArrayList<ListItem> listItems;

    public MyAdapter(Context context, ArrayList<ListItem> data){
        mContext = context;
        listItems = data;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public ListItem getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = layoutInflater.inflate(R.layout.listviewitem,null);


        TextView item_name = (TextView) view1.findViewById(R.id.item_name);
        TextView item_grade = (TextView) view1.findViewById(R.id.item_grade);

        item_name.setText(listItems.get(i).getItemName());
        item_grade.setText(listItems.get(i).getItemGrade());

        return view1;
    }
}
