package app.android.com.workinghours;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.DataHelper;

public class LogInActivity extends Activity {

    EditText editEmail,editPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editEmail=(EditText)findViewById(R.id.editemail1);
        editPass=(EditText)findViewById(R.id.editpass1);
    }
    public void onLogin(View v) {
        String email = editEmail.getText().toString();
        String pass = editPass.getText().toString();

        if (email.equals("")) {
            editEmail.setError("You must enter your email");
            editEmail.requestFocus();
        }
            else if (pass.equals("")) {
            editPass.setError("You must enter your password");
            editPass.requestFocus();
        }
        else {
            DataHelper handler = new DataHelper(this);
            SQLiteDatabase db = handler.getReadableDatabase();

            String sqlcmd = "select * from " + DataHelper.TABUSER + " where " + DataHelper.COL3_EMAIL + "=? and " + DataHelper.COL5_PASS + "=?";
            String[] sargs = {email, pass};

            Cursor cr = db.rawQuery(sqlcmd, sargs);
            if (cr.moveToFirst()) {
                String fname = cr.getString(0);
                String lname = cr.getString(1);
                String Email = cr.getString(2);
                String phone = cr.getString(3);
                String workhr = cr.getString(5);
                Intent intent = new Intent(this, UserActivity.class);
                intent.putExtra("fname", fname);
                intent.putExtra("lname", lname);
                intent.putExtra("Email", Email);
                intent.putExtra("phone", phone);
                intent.putExtra("workhr", workhr);
                editEmail.setText("");
                editPass.setText("");
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
            cr.close();
            db.close();
        }
    }

}
