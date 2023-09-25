package work.database;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "equipment.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "equip"; // название таблицы в бд
    // названия столбцов
    private Context context;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_TITLE = "title";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, SCHEMA);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE equip (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CLASS
                + " INTEGER, " + COLUMN_TITLE + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_CLASS
                + ", " + COLUMN_TITLE  + ") VALUES (1, 'Компьютер');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    void addEquip(int lab, String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLASS, lab);
        cv.put(COLUMN_TITLE, title);
        long result = db.insert(TABLE,null, cv);
        if (result == -1){
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
