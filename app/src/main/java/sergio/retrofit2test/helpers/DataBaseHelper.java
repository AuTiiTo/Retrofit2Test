package sergio.retrofit2test.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author s.ruiz
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Users.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS UserNames(_id integer primary key, username text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void saveSearchTerm(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!exist(db, user)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", user);
            db.insert("UserNames", null, contentValues);
        }
        db.close();
    }

    public Cursor getSuggestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from UserNames", null);
        return c;
    }

    private boolean exist(SQLiteDatabase db, String user) {
        boolean response = false;
        Cursor c = db.rawQuery("select * from UserNames", null);
        while (c.moveToNext()) {
            if (c.getString(1).equals(user)) {
                response = true;
            }
        }
        c.close();
        return response;
    }
}
