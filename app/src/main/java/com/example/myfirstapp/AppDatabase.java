package com.example.myfirstapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
        public abstract TaskDao taskDao();
        private static volatile AppDatabase appDatabase;

        public static synchronized AppDatabase getInstance(Context context){
            if (appDatabase == null){
               appDatabase = Room.databaseBuilder(context,
                        AppDatabase.class, "database-name").allowMainThreadQueries().build();
            }

            return appDatabase;
        }
    }

