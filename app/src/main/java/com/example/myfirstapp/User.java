package com.example.myfirstapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "taskId")
    public int id;

    @ColumnInfo(name = "name")
    String userName;
}
