package app.android.com.workinghours;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.DataHelper;

public class SignUpActivity extends Activity {
    EditText editFname, editLname, editPhone, editEmail, editPass, editCpass;
    String workhr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editFname=(EditText)findViewById(R.id.editfname);
        editLname=(EditText)findViewById(R.id.editlname);
        editEmail=(EditText)findViewById(R.id.editemail);
        editPhone=(EditText)findViewById(R.id.editphone);
        editPass=(EditText)findViewById(R.id.editpass);
        editCpass=(EditText)findViewById(R.id.editcpass);

    }

    public void onSubmit(View v)
    {
        final String fname=editFname.getText().toString();
        final String lname=editLname.getText().toString();
        final String email=editEmail.getText().toString();
        final String phone=editPhone.getText().toString();
        final String pass=editPass.getText().toString();
        final String cpass=editCpass.getText().toString();
        if (fname.equals("")) {
            editFname.setError("You must enter your first name");
            editFname.requestFocus();
        } else if (lname.equals("")) {
            editLname.setError("You must enter your last name");
            editLname.requestFocus();
        } else if (email.equals("")) {
            editEmail.setError("You must enter your email");
            editEmail.requestFocus();
        }else if (phone.equals("")) {
            editPhone.setError("You must enter your phone");
            editPhone.requestFocus();
        }
        else if (pass.equals("")) {
            editPass.setError("You must enter your password");
            editPass.requestFocus();
        } else if(!pass.equals(cpass)) {
            editCpass.setError("You must confirm your password");
            editCpass.requestFocus();
        }
        else
        {
            DataHelper handler=new DataHelper(this);
            SQLiteDatabase db=handler.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(DataHelper.COL1_FNAME,fname);
            cv.put(DataHelper.COL2_LNAME,lname);
            cv.put(DataHelper.COL3_EMAIL,email);
            cv.put(DataHelper.COL4_PHONE,phone);
            cv.put(DataHelper.COL5_PASS,pass);
            cv.put(DataHelper.COL6_WORKHR,"0 Hours 0 Minutes");
            long l=db.insert(DataHelper.TABUSER,null,cv);
            if(l>0)
            {
                Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show();
                editFname.setText("");
                editLname.setText("");
                editEmail.setText("");
                editPhone.setText("");
                editPass.setText("");
                editCpass.setText("");
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
