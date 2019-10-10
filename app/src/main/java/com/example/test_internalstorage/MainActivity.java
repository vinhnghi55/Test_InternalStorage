package com.example.test_internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSaveData, btnReadData;
    private TextView tvData;

    private final String fileName = "vinhnghi.com";
    private final String contnet = "Đẹp trai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidget();

        btnSaveData.setOnClickListener(this);
        btnReadData.setOnClickListener(this);
    }

    private void setWidget() {
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        btnReadData = (Button) findViewById(R.id.btn_read_data);
        tvData = (TextView) findViewById(R.id.tv_data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_data:
//                saveData();
                saveDataByCache();
                break;
            case R.id.btn_read_data:
//                readData();
                readData2();
                break;
            default:
                break;
        }
    }

    public void saveData() {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(contnet.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save data successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDataByCache() {
        FileOutputStream fileOutputStream = null;
        File file = null;

        try {
            file = new File(getCacheDir(), fileName);
            Log.d("MainActivity", getCacheDir().getAbsolutePath());

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(contnet.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save data successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = openFileInput(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            Log.d("MainAvtivity", stringBuffer.toString());
            tvData.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData2() {
        FileInputStream fileInputStream = null;

        try {
            File file = new File(getFilesDir(), fileName);
            fileInputStream = openFileInput(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            Log.d("MainAvtivity", stringBuffer.toString());
            tvData.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
