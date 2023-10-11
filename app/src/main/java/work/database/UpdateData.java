package work.database;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {
    EditText title_input, lab_input;
    Button update_button, delete_button;
    String id, lab, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        title_input = findViewById(R.id.equip_edit);
        lab_input = findViewById(R.id.lab_edit);
        update_button = findViewById(R.id.updateButton);
        delete_button = findViewById(R.id.deleteButton);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle(title);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateData.this);
                title = title_input.getText().toString().trim();
                lab = lab_input.getText().toString().trim();
                myDB.updateData(id, lab, title);
                finish();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("lab") && getIntent().hasExtra("title")){
            id = getIntent().getStringExtra("id");
            lab = getIntent().getStringExtra("lab");
            title = getIntent().getStringExtra("title");

            title_input.setText(title);
            lab_input.setText(lab);

            Log.d("stev", title+" "+lab);
        } else {
            Toast.makeText(this, "Нет данных", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить " + title + " ?");
        builder.setMessage("Вы уверены, что хотите удалить " + title + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateData.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}