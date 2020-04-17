package com.example.customtablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.example.customtablayoutdemo.paramimport.ParamImportActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> paths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "5G NR Site Template.xls");
        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "移动LTESite-公司附近真假-201907010.xls");
        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "珠海" + File.separator + "LTE SITE-珠海电信 - 15000个.xls");
        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "珠海" + File.separator + "珠海WCDMA.txt");
        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "珠海" + File.separator + "珠海电信_CDMAEVDO-公司附近.xls");
        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "珠海" + File.separator + "珠海联通_GSM-公司附近.txt");
        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "珠海" + File.separator + "珠海联通_GSM-公司附近.txt");
        paths.add(Environment.getExternalStorageDirectory() + File.separator + "base" + File.separator + "珠海" + File.separator + "珠海移动_TD-公司附近.txt");
        findViewById(R.id.btn_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParamImportActivity.class);
                intent.putStringArrayListExtra("mFilePaths", (ArrayList<String>) paths);
                intent.putExtra("mapType", 0);
                startActivity(intent);
            }
        });
    }
}
