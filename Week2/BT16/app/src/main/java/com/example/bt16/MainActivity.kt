package com.example.bt16 // Your package name

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // UI elements
    private lateinit var editTextEmployeeId: EditText
    private lateinit var editTextEmployeeName: EditText
    private lateinit var radioGroupEmployeeType: RadioGroup
    private lateinit var radioButtonFullTime: RadioButton
    private lateinit var radioButtonPartTime: RadioButton
    private lateinit var buttonAddEmployee: Button
    private lateinit var listViewEmployees: ListView

    // Data and adapter
    private lateinit var employeeList: ArrayList<Employee>
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect to UI elements
        editTextEmployeeId = findViewById(R.id.editTextEmployeeId)
        editTextEmployeeName = findViewById(R.id.editTextEmployeeName)
        radioGroupEmployeeType = findViewById(R.id.radioGroupEmployeeType)
        radioButtonFullTime = findViewById(R.id.radioButtonFullTime)
        radioButtonPartTime = findViewById(R.id.radioButtonPartTime)
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee)
        listViewEmployees = findViewById(R.id.listViewEmployees)

        // Initialize employee list
        employeeList = ArrayList()

        // Add some sample employees
        employeeList.add(Employee("m1", "NGuyen Thi Teo", "FullTime"))
        employeeList.add(Employee("m2", "Tran Van Ty", "PartTime"))
        employeeList.add(Employee("m3", "Ho Do", "FullTime"))

        // Create and set custom adapter
        adapter = EmployeeAdapter(this, employeeList)
        listViewEmployees.adapter = adapter

        // Set button click listener
        buttonAddEmployee.setOnClickListener {
            addEmployee()
        }

        // Set ListView item click listener
        listViewEmployees.setOnItemClickListener { parent, view, position, id ->
            val employee = employeeList[position]
            Toast.makeText(this, "Selected: ${employee.name}", Toast.LENGTH_SHORT).show()
        }

        // Set ListView long-click listener for delete
        listViewEmployees.setOnItemLongClickListener { parent, view, position, id ->
            deleteEmployee(position)
            true
        }
    }

    private fun addEmployee() {
        // Get input values
        val id = editTextEmployeeId.text.toString().trim()
        val name = editTextEmployeeName.text.toString().trim()

        // Validate inputs
        if (id.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã nhân viên!", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên nhân viên!", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if employee ID already exists
        if (employeeList.any { it.id == id }) {
            Toast.makeText(this, "Mã nhân viên đã tồn tại!", Toast.LENGTH_SHORT).show()
            return
        }

        // Get selected employee type from RadioGroup
        val type = when (radioGroupEmployeeType.checkedRadioButtonId) {
            R.id.radioButtonFullTime -> "FullTime"
            R.id.radioButtonPartTime -> "PartTime"
            else -> "FullTime"
        }

        // Create new employee object
        val newEmployee = Employee(id, name, type)

        // Add to list
        employeeList.add(newEmployee)

        // Notify adapter to refresh ListView
        adapter.notifyDataSetChanged()

        // Clear input fields
        editTextEmployeeId.text.clear()
        editTextEmployeeName.text.clear()
        radioButtonFullTime.isChecked = true  // Reset to default

        // Show success message
        Toast.makeText(this, "Đã thêm nhân viên: ${newEmployee.name}", Toast.LENGTH_SHORT).show()
    }

    private fun deleteEmployee(position: Int) {
        val employee = employeeList[position]

        // Remove from list
        employeeList.removeAt(position)

        // Notify adapter
        adapter.notifyDataSetChanged()

        // Show confirmation
        Toast.makeText(this, "Đã xóa: ${employee.name}", Toast.LENGTH_SHORT).show()
    }
}
