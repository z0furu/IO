package com.example.user.io;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {

    private EditText ed;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv = (TextView) findViewById(R.id.main2_tv);

        ed = (EditText) findViewById(R.id.edit);

    }

    public void test3(View view){
        String data  = ed.getText().toString();
        try{
            FileOutputStream fout = openFileOutput("mydata.txt",MODE_PRIVATE);
            fout.write(data.getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"save ok2",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void test4(View view){
        try {
            FileInputStream fin = openFileInput("mydata.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

            String str = null;
            while ((str = reader.readLine()) != null) {
                tv.append(str + "\n");
            }


            fin.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
