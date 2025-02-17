package com.opgkukic.buzzkeeper.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.ToDo;

import java.util.List;

public class ToDoAdapter extends BaseAdapter {

    private Activity activity;
    private List<ToDo> toDoList;

    public ToDoAdapter(Activity activity, List<ToDo> toDoList) {
        this.activity = activity;
        this.toDoList = toDoList;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for each item in the list
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_todo, parent, false);
        }

        // Get the current To-Do task (ToDo) object
        ToDo toDo = toDoList.get(position);

        // Bind the data to the views
        TextView tvZadataka = convertView.findViewById(R.id.tvZadataka);
        CheckBox cbStatus = convertView.findViewById(R.id.cbStatus);

        tvZadataka.setText(toDo.getZadataka());
        cbStatus.setChecked(toDo.isStatus());

        return convertView;
    }
}
