package com.clara.simple_todo_list_with_fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * Fragment for adding a new To Do item to the list
 */

public class AddToDoItemFragment extends Fragment {

	public interface NewItemCreatedListener {
		void newItemCreated(ToDoItem newItem);
	}

	private static final String TAG = "Add To Do Item Fragment";

	private NewItemCreatedListener mNewItemListener;

	// create newInstance method for instantiating an instance of this Fragment
	// Completed

	public AddToDoItemFragment() {

	}

	public static AddToDoItemFragment newInstance() {
		return new AddToDoItemFragment();
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		Log.d(TAG, "onAttach");

		if (context instanceof NewItemCreatedListener){    // Context is the hosting Activity.
			mNewItemListener = (NewItemCreatedListener) context;
			Log.d(TAG, "Listener set");
		} else  {
			throw new RuntimeException(context.toString() + " must implement NewItemCreatedListener");
		}
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_add_to_do_item, container, false);

		Log.d(TAG, "onCreateView");

		Button addItem = view.findViewById(R.id.add_todo_item_button);
		final EditText newItemText = view.findViewById(R.id.new_todo_item_edittext);
		final CheckBox urgentCheckbox = view.findViewById(R.id.urgent_checkbox);

		// create onClick event handler for button to create new ToDoItem from the data entered
		// Completed

		addItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// convert inputs to string, boolean, call activity to create new item
				String itemText = newItemText.getText().toString();

				if (itemText.isEmpty()) {
					Toast.makeText(getActivity(), "Please enter some text", Toast.LENGTH_LONG).show();
					return;
				}
				Boolean urgentCheck = urgentCheckbox.isChecked();
				ToDoItem newItem = new ToDoItem(itemText, urgentCheck);

				Log.d(TAG, "New item is " + newItem);

				newItemText.getText().clear();
				urgentCheckbox.setChecked(false);

				mNewItemListener.newItemCreated(newItem);
			}
		});

		// send NewItemCreatedListener message about the new item
		// Completed

		return view;

	}

	public void setNewItemCreatedListener(NewItemCreatedListener listener) {
		this.mNewItemListener = listener;
	}
}
