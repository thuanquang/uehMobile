package com.example.bai3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView adapter for displaying list of dogs
 */
class DogRecyclerAdapter(
    private val context: Context,
    private val dogList: List<DogModel>,
    private val onItemClick: (DogModel) -> Unit
) : RecyclerView.Adapter<DogRecyclerAdapter.DogViewHolder>() {

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(android.R.id.text1)
        val breedTextView: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(dog: DogModel, onItemClick: (DogModel) -> Unit) {
            nameTextView.text = dog.name
            breedTextView.text = dog.breed
            itemView.setOnClickListener { onItemClick(dog) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogList[position], onItemClick)
    }

    override fun getItemCount(): Int = dogList.size
}
