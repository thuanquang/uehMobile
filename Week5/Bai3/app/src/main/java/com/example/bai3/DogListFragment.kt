package com.example.bai3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragment displaying a list of dogs in a RecyclerView
 */
class DogListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DogRecyclerAdapter
    private var currentDog: DogModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewDogs)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dogList = getDogList()
        adapter = DogRecyclerAdapter(requireContext(), dogList) { dog ->
            currentDog = dog
            showDogDetail(dog)
        }
        recyclerView.adapter = adapter
    }

    private fun getDogList(): List<DogModel> {
        return listOf(
            DogModel(1, "Max", "Golden Retriever", 5, "Friendly and energetic family dog"),
            DogModel(2, "Bella", "Labrador Retriever", 3, "Loyal and intelligent companion"),
            DogModel(3, "Charlie", "German Shepherd", 4, "Protective and athletic"),
            DogModel(4, "Lucy", "Beagle", 2, "Small, playful and curious"),
            DogModel(5, "Cooper", "Husky", 3, "Energetic and beautiful"),
            DogModel(6, "Daisy", "Dachshund", 4, "Small and affectionate"),
            DogModel(7, "Rocky", "Boxer", 5, "Strong and devoted"),
            DogModel(8, "Bailey", "Poodle", 2, "Intelligent and trainable")
        )
    }

    private fun showDogDetail(dog: DogModel) {
        val fragment = DogDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("dog_id", dog.id)
                putString("dog_name", dog.name)
                putString("dog_breed", dog.breed)
                putInt("dog_age", dog.age)
                putString("dog_description", dog.description)
            }
        }
        
        parentFragmentManager.beginTransaction()
            .replace(R.id.viewPagerDogs, fragment)
            .addToBackStack(null)
            .commit()
    }
}
