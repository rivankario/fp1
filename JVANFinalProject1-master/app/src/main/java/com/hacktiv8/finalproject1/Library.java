package com.hacktiv8.finalproject1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Library extends AppCompatActivity {

    public List<ModelActivity> List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        String datanama = getIntent().getStringExtra("DataNama");

        // Action Bar Back
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setTitle("Hello!");

        Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();
        List<ModelActivity> clubList = new ArrayList<ModelActivity>();

        RecyclerView videoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        AdapterActivity clubAdapter = new AdapterActivity(getApplicationContext(), clubList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        videoRecyclerView.setLayoutManager(layoutManager);
        videoRecyclerView.setAdapter(clubAdapter);
    }

    // Membuat Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Action Option Menu Saat Salah Satu Di Tekan
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAdd){
            showAddDialog(this);
        } else if (item.getItemId() == R.id.menuKeluar){
            startActivity(new Intent(Library.this, MainActivity.class));
        }
        return true;
    }

    private void showAddDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        taskEditText.setHint("Add toDo List");
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add List")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        if (task.equals("")){
                            Toast.makeText(c,"Name Must Be Filled", Toast.LENGTH_SHORT).show();
                        } else{
                            List.add(new ModelActivity(task, "test", "test", "video1"));
                            RecyclerView videoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            AdapterActivity todoAdapter = new AdapterActivity(getApplicationContext(), List);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            videoRecyclerView.setLayoutManager(layoutManager);
                            videoRecyclerView.setAdapter(todoAdapter);
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }




}