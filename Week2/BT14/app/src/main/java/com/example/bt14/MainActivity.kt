package com.example.bt14 // Your package name here

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // UI elements
    private lateinit var editTextName: EditText
    private lateinit var buttonAdd: Button
    private lateinit var textViewPosition: TextView
    private lateinit var listViewNames: ListView

    // Data storage
    private lateinit var nameList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect to UI elements
        editTextName = findViewById(R.id.editTextName)
        buttonAdd = findViewById(R.id.buttonAdd)
        textViewPosition = findViewById(R.id.textViewPosition)
        listViewNames = findViewById(R.id.listViewNames)

        // Initialize ArrayList (starts empty)
        nameList = ArrayList()

        // Create ArrayAdapter to connect ArrayList to ListView
        adapter = ArrayAdapter(
            this,                                    // Context
            android.R.layout.simple_list_item_1,    // Built-in layout for list items
            nameList                                 // Data source
        )

        // Connect adapter to ListView
        listViewNames.adapter = adapter

        // Set button click listener
        buttonAdd.setOnClickListener {
            addName()
        }

        // Set ListView item click listener
        listViewNames.setOnItemClickListener { parent, view, position, id ->
            val selectedName = nameList[position]
            Toast.makeText(this, "Clicked: $selectedName", Toast.LENGTH_SHORT).show()
        }

        // Update position display
        updatePositionDisplay()
    }

    // Function to add name to list
    private fun addName() {
        // Get text from input field
        val name = editTextName.text.toString().trim()

        // Validate: check if empty
        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show()
            return
        }

        // Add name to ArrayList
        nameList.add(name)

        // Notify adapter that data changed (updates ListView)
        adapter.notifyDataSetChanged()

        // Clear input field
        editTextName.text.clear()

        // Update position display
        updatePositionDisplay()

        // Show confirmation
        Toast.makeText(this, "Đã thêm: $name", Toast.LENGTH_SHORT).show()
    }

    // Function to update position display
    private fun updatePositionDisplay() {
        val position = nameList.size
        val lastValue = if (nameList.isNotEmpty()) nameList.last() else ""
        textViewPosition.text = "position: $position, value: $lastValue"
    }
}
