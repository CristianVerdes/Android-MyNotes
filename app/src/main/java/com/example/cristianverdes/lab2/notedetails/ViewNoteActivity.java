package com.example.cristianverdes.lab2.notedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.cristianverdes.lab2.App;
import com.example.cristianverdes.lab2.R;
import com.example.cristianverdes.lab2.database.MarsDataBase;
import com.example.cristianverdes.lab2.model.Note;

/**
 * Created by Cristian Verdes on 11/3/2016.
 */

public class ViewNoteActivity extends AppCompatActivity {
    private static final String KEY_NOTE_ID = "noteId";
    private TextView title;
    private TextView data;

    public static void start(Context context, int noteId) {
        Intent starter = new Intent(context, ViewNoteActivity.class);
        starter.putExtra(KEY_NOTE_ID, noteId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        title = (TextView) findViewById(R.id.title);
        data = (TextView) findViewById(R.id.data);
        getSupportActionBar().hide();

        int noteId = this.getIntent().getExtras().getInt(KEY_NOTE_ID);
        MarsDataBase marsDataBase = ((App) getApplication()).getMarsDataBase();
        Note note = marsDataBase.getNote(noteId);
        title.setText(note.getTile());
        title.setEnabled(false);
        data.setText(note.getData());
        data.setEnabled(false);

    }
}
