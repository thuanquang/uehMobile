package com.example.bai3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragment displaying detailed information about a single dog
 */
class DogDetailFragment : Fragment() {

    private lateinit var dogNameView: TextView
    private lateinit var dogBreedView: TextView
    private lateinit var dogAgeView: TextView
    private lateinit var dogDescriptionView: TextView
    private lateinit var buttonBack: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dogNameView = view.findViewById(R.id.textDogName)
        dogBreedView = view.findViewById(R.id.textDogBreed)
        dogAgeView = view.findViewById(R.id.textDogAge)
        dogDescriptionView = view.findViewById(R.id.textDogDescription)
        buttonBack = view.findViewById(R.id.buttonBack)

        // Load dog data from arguments
        arguments?.let {
            val name = it.getString("dog_name", "Unknown")
            val breed = it.getString("dog_breed", "Unknown")
            val age = it.getInt("dog_age", 0)
            val description = it.getString("dog_description", "No description")

            dogNameView.text = name
            dogBreedView.text = "Breed: $breed"
            dogAgeView.text = "Age: $age years"
            dogDescriptionView.text = description
        }

        buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
