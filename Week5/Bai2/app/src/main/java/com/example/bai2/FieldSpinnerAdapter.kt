package com.example.bai2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Custom Spinner adapter for displaying form field options
 */
class FieldSpinnerAdapter(
    context: Context,
    private val fieldList: List<FieldModel>
) : ArrayAdapter<FieldModel>(context, 0, fieldList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        
        val field = fieldList[position]
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = field.fieldName
        
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        
        val field = fieldList[position]
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = field.fieldName
        
        return view
    }
}
