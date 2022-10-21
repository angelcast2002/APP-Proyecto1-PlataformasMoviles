package com.example.fordogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fordogs.databinding.ActivityMainBinding
import com.example.fordogs.ui.fragments.addevents.AddEventFragment
import com.example.fordogs.ui.fragments.calendar.CalendarFragment
import com.example.fordogs.ui.fragments.events.EventsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.main_fragment_view)
        setListeners(navController)
    }

    private fun setListeners(navController: NavController) {

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_calendar -> {
                    navController.navigate(R.id.calendarFragment)
                    true
                }
                R.id.bottom_nav_dogs -> {
                    navController.navigate(R.id.eventsFragment)
                    true
                }
            }

            true
        }

        binding.mainActivityFAB.setOnClickListener {
            navController.navigate(R.id.addEventFragment)
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_fragment_view, fragment)
        }
    }

}