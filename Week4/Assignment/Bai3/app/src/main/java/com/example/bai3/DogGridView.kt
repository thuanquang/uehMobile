package com.example.bai3

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView

class DogGridView(val activity: Activity, val listDogs: List<DogModel>) :
    ArrayAdapter<DogModel>(activity, R.layout.item_dog, listDogs) {

    override fun getCount(): Int {
        return listDogs.count()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.item_dog, parent,false)

        val imgDog = view.findViewById<ImageView>(R.id.imgDog)
        if (listDogs[position].imgDog != 0){
            imgDog.setImageResource(listDogs[position].imgDog)
        }
        return view
    }
    }
