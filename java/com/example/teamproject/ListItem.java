package com.example.teamproject;

public class ListItem {
    private String itemName;
    private String itemGrade;

    public ListItem(String itemName, String itemGrade){
        this.itemName = itemName;
        this.itemGrade = itemGrade;
    }

    public String getItemName(){
        return this.itemName;
    }

    public String getItemGrade(){
        return this.itemGrade;
    }
}
