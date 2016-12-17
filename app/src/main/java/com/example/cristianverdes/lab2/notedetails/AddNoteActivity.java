package com.example.cristianverdes.lab2.notedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cristianverdes.lab2.App;
import com.example.cristianverdes.lab2.model.Note;
import com.example.cristianverdes.lab2.R;
import com.example.cristianverdes.lab2.database.MarsDataBase;

/**
 * Created by Cristian Verdes on 10/27/2016.
 */

public class AddNoteActivity extends AppCompatActivity {
    protected EditText title;
    protected EditText data;
    protected Button button;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddNoteActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().setTitle("Add Notes");

        final MarsDataBase marsDataBase = ((App) getApplication()).getMarsDataBase();
        title = (EditText) findViewById(R.id.title);
        data = (EditText) findViewById(R.id.data);

        button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note();
                note.setTile(title.getText().toString());
                note.setData(data.getText().toString());
                marsDataBase.createNote(note);
                Toast.makeText(AddNoteActivity.this, "Note Added",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
