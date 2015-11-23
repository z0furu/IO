package com.example.user.io;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SDcard extends AppCompatActivity {

    private TextView tv;
    private EditText edview;
    private File sdroot, approot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv = (TextView) findViewById(R.id.sd_tv);
        edview = (EditText) findViewById(R.id.sd_ed);

        String state = Environment.getExternalStorageState(); //獲取SD訊息
        Log.e("brad",state);

        sdroot  = Environment.getExternalStorageDirectory(); //獲取SDCARD 路徑，每家路徑都不一樣
        Log.e("brad", sdroot.getAbsolutePath());

        approot = new File(sdroot,"Android/data/" + getPackageName() +"/");//getPackageName 得到這個應用程式的名稱
        if ((!approot.exists())){
            approot.mkdirs();
        }

    }

    public void test5(View view){
        //注意!!
        // sdroot的路徑與approot路徑並不一樣，sdroot是在記憶卡底下創建一個mydata2.txt
        //approot是在這一個在這個應用程式底下
        File newfile = new File(sdroot,"mydata2.txt");//創建一個檔案
        String ed = edview.getText().toString();//要寫入的資料
        try{
            FileOutputStream fout = new FileOutputStream(newfile);
            fout.write(ed.getBytes());              //以Byte型態輸入
            fout.flush();                           //刷新
            fout.close();                           //關閉Stream
            Toast.makeText(this,"SD OK",Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void test6(View view){
        //會跟著APP，
        String ed = edview.getText().toString();
        File newfile = new File(approot,"mydata2.txt");
        try{
            FileOutputStream fout = new FileOutputStream(newfile);
            fout.write(ed.getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"SD OK",Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void test7(View view){
        tv.setText("");
        try {
            File newfile = new File(approot,"mydata2.txt");
            FileInputStream fin = new FileInputStream(newfile);

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
