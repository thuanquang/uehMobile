package com.example.bai2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

/**
 * Main Activity for the form validation application
 * Hosts the FormFragment using FragmentContainerView
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add FormFragment if not already added
        if (savedInstanceState == null) {
            val formFragment = FormFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, formFragment)
                .commit()
        }
    }
}
