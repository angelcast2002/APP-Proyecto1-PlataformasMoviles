package com.example.fordogs.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.fordogs.R
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.repository.perroTipsRepo.PerroTipsRepository
import com.example.fordogs.data.repository.perroTipsRepo.PerroTipsRepsitoryImpl
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import com.example.fordogs.databinding.ActivityMainBinding
import com.example.fordogs.ui.fragments.addevents.AddEventBottomSheetFragment
import com.example.fordogs.ui.fragments.addevents.EventsManagementViewModel
import com.example.fordogs.ui.util.dataStore
import com.example.fordogs.ui.util.removePreferencesValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var eventsVM:EventsManagementViewModel
    @Inject
    lateinit var repository: UserPerroRepository
    @Inject
    lateinit var userPerroTips: PerroTipsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        eventsVM = ViewModelProvider(this)[EventsManagementViewModel::class.java]

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
                            lifecycleScope.launch {
                                val data = repository.getUserPerroInfo().data
                                if (data != null) {
                                    logOut()
                                    repository.logOut(data)
                                    userPerroTips.logOut()
                                }

                            }
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
            val addEventBottomSheetFragment = AddEventBottomSheetFragment()
            addEventBottomSheetFragment.setEventId(0, false)
            addEventBottomSheetFragment.show(supportFragmentManager, addEventBottomSheetFragment.tag)
        }

    }

    private fun logOut() {
        lifecycleScope.launch{
            dataStore.removePreferencesValue()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_fragment_view, fragment)
        }
    }

}