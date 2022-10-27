package com.hacktiv8.finalproject1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnProfile;
    private Button btnLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hide Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnLibrary = (Button) findViewById(R.id.btnLibrary);
    }


    public void btnProfileClicked(View view){
        startActivity(new Intent(MainActivity.this, Profile.class));
    }

    public void btnLibraryPress(View view){
        Intent sendData1 = new Intent(MainActivity.this, Library.class);
        sendData1.putExtra("Data Nama", "User");
        startActivity(sendData1);

    }

}