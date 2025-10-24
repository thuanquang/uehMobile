package com.example.harrypotterbooks

import android.app.Activity
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class BookListView (val activity: Activity, val listBook: List<BookModel>) :
    ArrayAdapter<BookModel>(activity, R.layout.item_book, listBook) {

    override fun getCount(): Int {
        return listBook.size
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        //Override hàm getView() để custom layout cho mỗi item
        val view = activity.layoutInflater.inflate(R.layout.item_book, parent, false)

        val imgBook = view.findViewById<ImageView>(R.id.imgBook)
        if (listBook[position].imgBook != 0) {
            imgBook.setImageResource(listBook[position].imgBook)
        } else {
            //Glide.with(activity).load(listBook[position].urlBook).into(imgBook)
            Glide.with(activity).load(listBook[position].urlBook).into(imgBook)
        }

        val txtBookName = view.findViewById<TextView>(R.id.txtBookName)
        txtBookName.text = listBook[position].txtBookName

        return view
    }
    }
