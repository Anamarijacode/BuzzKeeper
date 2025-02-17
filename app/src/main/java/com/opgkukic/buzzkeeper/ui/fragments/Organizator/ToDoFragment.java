package com.opgkukic.buzzkeeper.ui.fragments.Organizator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.ToDo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ToDoFragment extends Fragment {

    private ListView listViewToDo;
    private Button btnAddToDo;

    private DatabaseReference toDoRef;
    private List<ToDo> toDoList;

    public ToDoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do2, container, false);

        listViewToDo = view.findViewById(R.id.listViewToDo);
        btnAddToDo = view.findViewById(R.id.btnAddToDo);

        toDoRef = FirebaseDatabase.getInstance().getReference("todo");
        toDoList = new ArrayList<>();

        // Button click to add a new To-Do task
        btnAddToDo.setOnClickListener(v -> {
            // Logic to add new To-Do task (could open a dialog or new activity)
            // After input, you would call a method to push it to Firebase.
        });

        // Load To-Do tasks from Firebase
        loadToDo();

        return view;
    }

    // Method to add a ToDo task
    public void addToDo(ToDo toDo) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // Generate a unique ID for the new ToDo task
        String toDoId = database.child("toDo").push().getKey();

        // Store the ToDo object in the Firebase database
        if (toDoId != null) {
            database.child("toDo").child(toDoId).setValue(toDo)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Task added successfully
                            Toast.makeText(getContext(), "ToDo added!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Failed to add task
                            Toast.makeText(getContext(), "Failed to add ToDo!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    // Method to delete a ToDo task
    public void deleteToDo(String toDoId) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // Remove the ToDo task from the database using its unique ID
        database.child("toDo").child(toDoId).removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Task deleted successfully
                        Toast.makeText(getContext(), "ToDo deleted!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Failed to delete task
                        Toast.makeText(getContext(), "Failed to delete ToDo!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void loadToDo() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("toDo");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ToDo> toDoList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ToDo toDo = snapshot.getValue(ToDo.class);
                    toDoList.add(toDo);
                }
                // Set the adapter to the ListView
                com.opgkukic.buzzkeeper.adapter.ToDoAdapter adapter = new com.opgkukic.buzzkeeper.adapter.ToDoAdapter(getActivity(), toDoList);
                listViewToDo.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

}
