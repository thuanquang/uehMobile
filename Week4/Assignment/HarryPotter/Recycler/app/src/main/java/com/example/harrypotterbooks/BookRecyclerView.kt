package com.example.harrypotterbooks

import android.app.Activity
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class BookRecyclerView (val activity: Activity, val listBook: List<BookModel>) :
    RecyclerView.Adapter<BookRecyclerView.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(listBook[position])
    }

    override fun getItemCount(): Int = listBook.size
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBook: ImageView = itemView.findViewById<ImageView>(R.id.imgBook)
        val txtBookName: TextView = itemView.findViewById<TextView>(R.id.txtBookName)

        fun bind(book: BookModel){
            if(book.imgBook != 0){
                imgBook.setImageResource(book.imgBook)
            } else {
                Glide.with(itemView.context).load(book.urlBook).into(imgBook)
            }
            txtBookName.text = book.txtBookName
        }
    }
}
