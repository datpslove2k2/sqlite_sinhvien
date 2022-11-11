package com.example.quanlysinhvien_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlysinhvien_sqlite.adapter.SinhvienAdapter;
import com.example.quanlysinhvien_sqlite.dao.SinhvienDao;
import com.example.quanlysinhvien_sqlite.model.Lop;
import com.example.quanlysinhvien_sqlite.model.Sinhvien;

import java.util.ArrayList;
import java.util.List;

public class ListsvActivity extends AppCompatActivity {
    private ListView lvsinhvien;
    private List<Sinhvien> sinhvienList;
    private SinhvienAdapter adapter;
    private SinhvienDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsv);
        lvsinhvien = (ListView) findViewById(R.id.lv_sv);

        sinhvienList = new ArrayList<Sinhvien>();
        dao = new SinhvienDao(ListsvActivity.this);
        Lop lop = new Lop();
        sinhvienList = dao.getsv(lop.getMalop());

        adapter = new SinhvienAdapter(getApplicationContext(),sinhvienList);
        lvsinhvien.setAdapter(adapter);
        // edit
        lvsinhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sinhvien svCheck = sinhvienList.get(i);
                Intent intent = new Intent(ListsvActivity.this, EditActivity.class);
                intent.putExtra("dataSV",svCheck);
                startActivity(intent);
            }
        });

        // delete
        lvsinhvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                Sinhvien svCheck = sinhvienList.get(i);
                dao.DeleteSinhvien("" + svCheck.getTensv());
                Toast.makeText(ListsvActivity.this, "Delete Item Success!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.them)
        {
            Intent intent = new Intent(ListsvActivity.this,AddActivity.class);
            startActivity(intent);

        }
        if(id == R.id.btn_Back)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sinhvienList.clear();
        sinhvienList.addAll(dao.getALL());
        adapter.notifyDataSetChanged();

    }
}