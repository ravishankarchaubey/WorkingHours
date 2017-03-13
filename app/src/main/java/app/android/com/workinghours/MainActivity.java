package app.android.com.workinghours;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSignUp(View v)
    {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLogIn(View v)
    {
        Intent intent=new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
}
