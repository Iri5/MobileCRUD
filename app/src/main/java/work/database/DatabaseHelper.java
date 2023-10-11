package work.database;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "equipment.db"; // название бд
    private static final int SCHEMA = 2; // версия базы данных
    static final String TABLE = "equip"; // название таблицы в бд
    // названия столбцов
    private Context context;
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CLASS = "class";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_TYPE = "type";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, SCHEMA);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE equip (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CLASS
                + " INTEGER, " + COLUMN_TITLE + " TEXT, " + COLUMN_TYPE + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_CLASS
                + ", " + COLUMN_TITLE  + ", " + COLUMN_TYPE + ") VALUES ('1', 'Xiaomi Mi Smart Projector 2', 'Компьютер');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    void addEquip(String lab, String title, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLASS, lab);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TYPE, type);
        long result = db.insert(TABLE,null, cv);
        if (result == -1){
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE + " ORDER BY " + COLUMN_CLASS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String lab, String title, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String row_id_trim = row_id.trim();

        cv.put(COLUMN_CLASS, lab);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TYPE, type);
        long result = db.update(TABLE, cv, "_id" + "=" + row_id_trim, null);
        if (result == -1){
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Ошибка удаления", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешно удалено", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE);
    }
}
