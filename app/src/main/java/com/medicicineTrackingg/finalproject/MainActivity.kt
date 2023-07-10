package com.medicicineTrackingg.finalproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var homeFragment: HomeFragment
    private lateinit var pharmacyFragment: PharmacyFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var bottomNavigationView : BottomNavigationView
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instanciação dos fragmentos
        homeFragment = HomeFragment()
        pharmacyFragment = PharmacyFragment()
        settingsFragment = SettingsFragment()

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener (this)
        setFragment(homeFragment)
    }

   // Abaixo a função que selecciona um determinado fragmento
    private  fun  setFragment(fragment: Fragment){
        val fragmentTransation = supportFragmentManager.beginTransaction()
        fragmentTransation.replace(R.id.frame_fragments, fragment)
        fragmentTransation.commit()
    }

    // Abaixo é chamado cada fragmento com base o parâmetro
override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home ->{
                setFragment(homeFragment)
            }

            R.id.menu_pharmacies ->{
                setFragment(pharmacyFragment)
            }

            R.id.menu_settings ->{
                setFragment(settingsFragment)
            }
        }
        return true
    }


}