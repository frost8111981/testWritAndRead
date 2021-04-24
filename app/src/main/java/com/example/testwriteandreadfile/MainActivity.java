package com.example.testwriteandreadfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    FileOutputStream outputStream;
    InputStreamReader inputStreamReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.textView2);
        EditText editName = findViewById(R.id.Name);
        Button save = findViewById(R.id.save);
        Button read = findViewById(R.id.read);
        EditText editSurname = findViewById(R.id.Surname);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = editSurname.getText().toString();
                String text = editName.getText().toString() + " " + editSurname.getText().toString();
                Log.d("MyLog", filename);
                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(text.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                editName.setText("");
                editSurname.setText("");
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String filename = editSurname.getText().toString();
                    inputStreamReader = new InputStreamReader(openFileInput(filename));
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    text.setText(line);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}