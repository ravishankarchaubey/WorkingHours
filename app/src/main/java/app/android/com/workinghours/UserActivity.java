package app.android.com.workinghours;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import database.DataHelper;

public class UserActivity extends Activity {
    TextView ufname,ulname,uphone,uemail,uworkhr;
    String fname,lname,phone,email,workhr,whr;
    int hr=-1,min=-1,thr=-1,tmin=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ufname=(TextView)findViewById(R.id.ufname);
        ulname=(TextView)findViewById(R.id.ulname);
        uemail=(TextView)findViewById(R.id.uemail);
        uphone=(TextView)findViewById(R.id.uphone);
        uworkhr=(TextView)findViewById(R.id.uworkhr);

        Intent intent=getIntent();
        fname=intent.getStringExtra("fname");
        lname=intent.getStringExtra("lname");
        phone=intent.getStringExtra("phone");
        email=intent.getStringExtra("Email");
        workhr=intent.getStringExtra("workhr");

        ufname.setText("First Name : "+fname);
        ulname.setText("Last Name : "+lname);
        uphone.setText("Contact No : "+phone);
        uemail.setText("User ID : "+email);
        uworkhr.setText("Working Hours : "+workhr);
    }

    public void onTime(View v) {
        Calendar calendar = Calendar.getInstance();
        int hr = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                UserActivity.this.hr = hourOfDay;
                UserActivity.this.min = minute;
            }
        }, hr, min, true);
        tpd.show();
    }

    public void onTime1(View v) {
        Calendar calendar = Calendar.getInstance();
        int thr = calendar.get(Calendar.HOUR_OF_DAY);
        int tmin = calendar.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                UserActivity.this.thr = hourOfDay;
                UserActivity.this.tmin = minute;
            }
        }, thr, tmin, true);
        tpd.show();
    }

    private void showDialog(String msg, String title) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(title);
        b.setMessage(msg);
        b.setPositiveButton("OK", null);
        b.show();
    }

    public void onUpdate(View v) {
        if (hr == -1 || min == -1 )
            showDialog("Please choose your initial time", "Alert!");
        if (thr == -1 || tmin == -1 )
            showDialog("Please choose your final time", "Alert!");
        else
            Update();
    }

    private void Update() {
        int time1=thr*60*60+tmin*60;
        int time2=hr*60*60+min*60;
        int hr=(time1-time2)/3600;
        int min=(time1-time2)/60-hr*60;
        whr=hr+" Hours "+min+" Minutes";
        uworkhr.setText("Working Hours : "+hr+" Hours "+min+" Minutes");

        DataHelper handler = new DataHelper(this);
        SQLiteDatabase db = handler.getWritableDatabase();
        /*String sqlcmd = "update "+ DataHelper.TABUSER +" set " + DataHelper.COL6_WORKHR + "=?  where " + DataHelper.COL3_EMAIL+"=? ";
        String []sargs={whr,email};
        Cursor cr=db.rawQuery(sqlcmd, sargs);
        cr.close();*/
        ContentValues cv=new ContentValues();
        cv.put(DataHelper.COL6_WORKHR,whr);
        String []sargs={email};
        db.update(DataHelper.TABUSER,cv,DataHelper.COL3_EMAIL+"=? ",sargs);
        db.close();
    }
}