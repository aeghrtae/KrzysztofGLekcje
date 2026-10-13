package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private  EditText fileNameInput;
    private  EditText editor;
    private String currentFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText fileNameInput = findViewById(R.id.fileNameInput);
        EditText editor = findViewById(R.id.editor);




    }
    private void openFile(){



        String fileName= fileNameInput.getText().toString().trim();
        if(fileName.isEmpty()){
            //tu musi byc toast nie mozna otworzyc pliku
            return;

        }

        try{
            editor.setText(readFile(fileName));
            currentFile = fileName;
        } catch (IOException e){
            //tutaj toast nier mozna otworzyc pliku + e.getMessage();
        }

    }


    private String readFile(String fileName) throws IOException {
        File internal = new File(getFilesDir(), fileName);
            InputStream is;

            if(internal.exists()){
                is = new FileInputStream(internal);
            } else {
                is = getAssets().open(fileName);
            }

            StringBuilder content = new StringBuilder();
            try(BufferedReader reader = new BufferedReader( new InputStreamReader(is, StandardCharsets.UTF_8))){
                String line;

                while((line = reader.readLine()) != null){
                    content.append(line).append("\n");
                }


            }
        return content.toString();

    }


}