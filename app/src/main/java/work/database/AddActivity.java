package work.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//CRUD взаимодействие
public class AddActivity extends AppCompatActivity {

    EditText labBox, titleBox;

    Button saveButton;

    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        labBox = findViewById(R.id.lab);
        titleBox = findViewById(R.id.equip);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.addEquip(Integer.parseInt(labBox.getText().toString()), titleBox.getText().toString());
            }
        });

    }




}