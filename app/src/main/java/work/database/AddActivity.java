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
import android.widget.Toast;

//CRUD взаимодействие
public class AddActivity extends AppCompatActivity {
    EditText labBox, titleBox, typeBox;
    Button addButton;
    String lab, title, type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        labBox = findViewById(R.id.lab_add);
        titleBox = findViewById(R.id.equip_add);
        typeBox = findViewById(R.id.type_add);
        addButton = findViewById(R.id.saveButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lab = labBox.getText().toString().trim();
                title = titleBox.getText().toString().trim();
                type = typeBox.getText().toString().trim();
                if (title.isEmpty() || lab.isEmpty() || type.isEmpty()){
                    Toast.makeText(AddActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                    myDB.addEquip(lab, title, type);
                    finish();
                }
            }
        });
    }
}