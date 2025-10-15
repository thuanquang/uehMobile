package com.example.bt16 // Your package name

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class EmployeeAdapter(
    private val context: Context,
    private val employees: ArrayList<Employee>
) : BaseAdapter() {

    // Returns total number of items
    override fun getCount(): Int {
        return employees.size
    }

    // Returns item at specific position
    override fun getItem(position: Int): Any {
        return employees[position]
    }

    // Returns item ID (usually same as position)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Creates and returns the view for each list item
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get or create the view
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            // First time creating this view - inflate layout
            view = LayoutInflater.from(context).inflate(R.layout.list_item_employee, parent, false)

            // Create ViewHolder to cache view references
            holder = ViewHolder()
            holder.textViewEmployeeInfo = view.findViewById(R.id.textViewEmployeeInfo)

            // Store holder in view's tag
            view.tag = holder
        } else {
            // View already exists - reuse it
            view = convertView
            holder = view.tag as ViewHolder
        }

        // Get employee data for this position
        val employee = employees[position]

        // Set employee info to TextView
        holder.textViewEmployeeInfo.text = employee.toString()

        return view
    }

    // ViewHolder pattern - caches view references for better performance
    private class ViewHolder {
        lateinit var textViewEmployeeInfo: TextView
    }
}
