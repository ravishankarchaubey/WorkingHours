package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RAVISHANKAR CHAUBEY on 30-05-2016.
 */
public class DataHelper extends SQLiteOpenHelper {
    private final static int VERSION=1;
    public final static String DB="user";
    public final static String TABUSER="user_data";
    public final static String COL1_FNAME="fname";
    public final static String COL2_LNAME="lname";
    public final static String COL3_EMAIL="email";
    public final static String COL4_PHONE="phone";
    public final static String COL5_PASS="password";
    public final static String COL6_WORKHR="workhr";
    public DataHelper(Context context) {
        super(context,DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cmd=String.format("create table %s(%s text,%s text,%s text,%s text,%s text,%s text)",
                TABUSER,COL1_FNAME,COL2_LNAME, COL3_EMAIL,COL4_PHONE,COL5_PASS,COL6_WORKHR);
        db.execSQL(cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
