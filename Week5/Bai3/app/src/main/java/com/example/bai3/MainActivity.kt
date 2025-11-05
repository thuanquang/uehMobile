package com.example.bai3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Main Activity hosting ViewPager2 with dog gallery and detail tabs
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPagerDogs)
        tabLayout = findViewById(R.id.tabLayout)

        // Setup ViewPager2 adapter
        val adapter = DogViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        // Connect TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Dog List"
                1 -> "Dog Detail"
                else -> "Tab"
            }
        }.attach()
    }
}
