package com.example.cristianverdes.lab2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cristianverdes.lab2.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian Verdes on 11/2/2016.
 */

public class MarsDataBase extends SQLiteOpenHelper implements IDataBase {
    private static final String DATABASE_NAME = "MyDBName";
    private static final String NOTES_TABLE_NAME = "NOTES";
    private static final String NOTES_COLUMN_ID = "ID";
    private static final String NOTES_COLUMN_TITLE = "TITLE";
    private static final String NOTES_COLUMN_DATA = "DATA";

    public MarsDataBase(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + NOTES_TABLE_NAME + " ( " +
                NOTES_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                NOTES_COLUMN_TITLE + " TEXT, " +
                NOTES_COLUMN_DATA + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void createNote(Note note) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTES_COLUMN_TITLE, note.getTile());
        contentValues.put(NOTES_COLUMN_DATA, note.getData());
        database.insert(NOTES_TABLE_NAME, null, contentValues);
    }

    @Override
    public Note getNote(int id) {
        Note note = new Note();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor databaseCursor = database.rawQuery("SELECT * FROM NOTES WHERE ID=" + id, null );
        //Parse the cursor for one note
        if(databaseCursor.moveToFirst()){
            note.setTile(databaseCursor.getString(databaseCursor.getColumnIndex(NOTES_COLUMN_TITLE)));
            note.setData(databaseCursor.getString(databaseCursor.getColumnIndex(NOTES_COLUMN_DATA)));
            note.setId(databaseCursor.getInt(databaseCursor.getColumnIndex(NOTES_COLUMN_ID)));
        }
        databaseCursor.close();
        return note;
    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public void deleteNote(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(NOTES_TABLE_NAME, "ID = ?", new String[] {String.valueOf(id)} );
    }

    @Override
    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor databaseCursor = database.rawQuery("SELECT * FROM " + NOTES_TABLE_NAME, null);
        //Parse the cursor for all notes
        while(databaseCursor.moveToNext()){
            Note note = new Note();
            note.setTile(databaseCursor.getString(databaseCursor.getColumnIndex(NOTES_COLUMN_TITLE)));
            note.setData(databaseCursor.getString(databaseCursor.getColumnIndex(NOTES_COLUMN_DATA)));
            note.setId(databaseCursor.getInt(databaseCursor.getColumnIndex(NOTES_COLUMN_ID)));
            //Add note to list
            notes.add(note);
        }
        databaseCursor.close();
        return notes;
    }
}
