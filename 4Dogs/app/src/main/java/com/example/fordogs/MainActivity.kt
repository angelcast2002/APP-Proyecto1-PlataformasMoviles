package com.example.fordogs

import android.app.ProgressDialog.show
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
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
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.main_fragment_view)
        setListeners()
        activeItem()
    }

    private fun activeItem() {
        navController.addOnDestinationChangedListener() { _, destination, _ ->
            when (destination.id) {
                R.id.calendarFragment -> {
                    binding.bottomNavigationView.menu.getItem(0).isChecked = true
                }
                R.id.eventsFragment -> {
                    binding.bottomNavigationView.menu.getItem(1).isChecked = true
                }
            }
        }
    }

    private fun setListeners() {

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
                R.id.bottom_nav_settings ->{

                    val builder = AlertDialog.Builder(this)
                    builder.apply {
                        setTitle(getString(R.string.text_Advertencia))
                        setMessage(getString(R.string.text_mensaje_cerrarSesion))
                        setPositiveButton(getString(R.string.text_Eliminar)
                        ) { _, _ ->
                            navController.navigate(R.id.loginFragment)
                            finishAffinity() //Finaliza la app
                        }
                        setNegativeButton(getString(R.string.text_Cancelar)) { _, _ -> }
                        show()
                    }
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