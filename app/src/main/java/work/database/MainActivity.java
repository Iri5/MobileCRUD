 package work.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//предоставляет запрос и позволяет возвращать набор строк, которые соответствуют этому запросу.
import android.database.Cursor;
//База данных в SQLite, позволяет выполнять запросы к бд, выполнять с ней различные манипуляции.
import android.view.View;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addButton;
    DatabaseHelper myDB;
    ArrayList<String> equip_id, equip_lab, equip_title;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new DatabaseHelper(MainActivity.this);
        equip_id = new ArrayList<>();
        equip_lab = new ArrayList<>();
        equip_title = new ArrayList<>();

        storeDataInArray();
        customAdapter = new CustomAdapter(MainActivity.this, equip_id, equip_lab, equip_title);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                equip_id.add(cursor.getString(0));
                equip_lab.add(cursor.getString(1));
                equip_title.add(cursor.getString(2));

            }
        }
    }
}