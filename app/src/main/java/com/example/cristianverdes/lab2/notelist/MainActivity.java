package com.example.cristianverdes.lab2.notelist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cristianverdes.lab2.App;
import com.example.cristianverdes.lab2.model.Note;
import com.example.cristianverdes.lab2.R;
import com.example.cristianverdes.lab2.notedetails.AddNoteActivity;
import com.example.cristianverdes.lab2.help.HelpActivity;
import com.example.cristianverdes.lab2.notedetails.ViewNoteActivity;
import com.example.cristianverdes.lab2.settings.SettingsActivity;
import com.example.cristianverdes.lab2.database.MarsDataBase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int itemPosition;
    ArrayList<String> empthyList = new ArrayList<String>();
    NotesListAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag", "passed onCreate()");

        adapter = new NotesListAdapter(this, new ArrayList<Note>(){});
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewNoteActivity.start(MainActivity.this, (int) id);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                showDeleteDialog(arg3, pos);
                return true;
            }
        });

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setTitle("My Notes");

    }

    private void showDeleteDialog(final long noteId, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MarsDataBase marsDataBase = ((App) getApplication()).getMarsDataBase();
                        marsDataBase.deleteNote(((int) noteId));
                        adapter.delete(position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Are you sure you want to delete this note?");

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.notes:
                AddNoteActivity.start(this);
                return true;
            case R.id.settings:
                SettingsActivity.start(this);
                return true;
            case R.id.help:
                HelpActivity.start(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onStart(){
        super.onStart();
        Log.d("tag1", "passed onStart()");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("tag2", "passed onResume()");
        adapter.update(((App) getApplication()).getMarsDataBase().getNotes());
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("tag3", "passed onPause()");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("tag4", "passed onStop()");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("tag5", "passed onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos", itemPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        itemPosition = savedInstanceState.getInt("pos");
    }
}
