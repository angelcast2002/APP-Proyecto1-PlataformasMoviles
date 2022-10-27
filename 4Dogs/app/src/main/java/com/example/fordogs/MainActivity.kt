package com.example.fordogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.fordogs.databinding.ActivityMainBinding

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
                R.id.profileFragment -> {
                    binding.bottomNavigationView.menu.getItem(3).isChecked = true
                }
            }
        }
    }

    private fun setListeners() {

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_calendar -> {
                    navController.popBackStack(R.id.calendarFragment, true)
                    navController.navigate(R.id.calendarFragment)
                    true
                }
                R.id.bottom_nav_dogs -> {
                    navController.popBackStack(R.id.eventsFragment, true)
                    navController.navigate(R.id.eventsFragment)
                    true
                }
                R.id.bottom_nav_profile -> {
                    navController.popBackStack(R.id.profileFragment, true)
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.bottom_nav_logout ->{

                    val builder = AlertDialog.Builder(this)
                    builder.apply {
                        setTitle(getString(R.string.text_Advertencia))
                        setMessage(getString(R.string.text_mensaje_cerrarSesion))
                        setPositiveButton(getString(R.string.text_Si)
                        ) { _, _ ->
                            navController.popBackStack()
                            navController.navigate(R.id.loginFragment)
                        }
                        setNegativeButton(getString(R.string.text_Cancelar)) { _, _ ->
                            navController.navigate(R.id.calendarFragment)
                            true

                        }
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