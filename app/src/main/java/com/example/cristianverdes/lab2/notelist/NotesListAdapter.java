package com.example.cristianverdes.lab2.notelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cristianverdes.lab2.model.Note;
import com.example.cristianverdes.lab2.R;

import java.util.List;

/**
 * Created by Cristian Verdes on 11/2/2016.
 */

public class NotesListAdapter extends BaseAdapter{

    private final Context context;
    private final List<Note> notes;
    private final LayoutInflater layoutInflater;


    public NotesListAdapter(Context context, List<Note> notes){
        this.context = context;
        this.notes = notes;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) convertView;
        if(textView == null){
            textView = (TextView) layoutInflater.inflate(R.layout.list_row, parent, false);
        }
        textView.setText(getItem(position).getTile());
        return textView;
    }

    public void update(List<Note> notes){
        this.notes.clear();
        this.notes.addAll(notes);
        this.notifyDataSetChanged();
    }

    public void delete(int position){
        this.notes.remove(position);
        this.notifyDataSetChanged();
    }

}
