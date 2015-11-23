package com.example.user.io;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp ;  //偏好設定
    private SharedPreferences.Editor edit;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b1 = new Button(this);
        //Button b2 = new Button(this);


        sp = getSharedPreferences("mydata",MODE_PRIVATE);
        edit  = sp.edit();
        textView = (TextView) findViewById(R.id.tv);


    }


    public void test1(View view){
        edit.putBoolean("sound", false);
        edit.putString("username", "Brad");
        edit.putInt("stage", 4);
        edit.commit();

        Toast.makeText(this,"Save OK",Toast.LENGTH_LONG).show();
    }

    public void test2(View view){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //如果找不到值，就顯示預設的值
                boolean sound = sp.getBoolean("sound", false);
                String username = sp.getString("username", "");
                int stage = sp.getInt("stage", 1);
                textView.setText("User name:" + username + "\n" +
                        "sound" + (sound ? "On" : "Off" + "\n")
                        + "stage: " + stage);
                Log.d("test", "test");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
