package com.example.cristianverdes.lab2.database;

import com.example.cristianverdes.lab2.model.Note;

import java.util.List;

/**
 * Created by Cristian Verdes on 11/2/2016.
 */

public interface IDataBase {
    //CRUD
    void createNote(Note note);
    Note getNote(int id);
    void updateNote(Note note);
    void deleteNote(int id);

    //Get all notes
    List<Note> getNotes();

}
