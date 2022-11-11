package com.example.quanlysinhvien_sqlite.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper( Context context) {
        super(context, "QLSinhvien1.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreatelop ="Create table Lop(Malop nvarchar(50) primary key,Tenlop nvarchar(200) )";
        db.execSQL(sqlCreatelop);
        String sqlCreatesv="Create table Sinhvien(Tensv nvarchar(200) primary key,Namsinh nvarchar(200),lop nvarchar50 ,Diemtoan int,Diemtin int,DiemTA int,foreign key (lop) references Lop(Malop)  )";
        db.execSQL(sqlCreatesv);
        String insertlop="Insert into Lop values('ML001','Lớp 20T1')";
        db.execSQL(insertlop);
        String insertsv="Insert into Sinhvien values('Trần Tiến Đạt','2002','ML001',7,9,9)";
        db.execSQL(insertsv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
