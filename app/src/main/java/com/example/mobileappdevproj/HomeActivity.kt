package com.example.mobileappdevproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mobileappdevproj.Fragment.HomeFragment
import com.example.mobileappdevproj.Fragment.TransactionFragment
import com.example.mobileappdevproj.Fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private val HomeFragment = HomeFragment()
    private val TransactionFragment = TransactionFragment()
    private val ProfileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(HomeFragment)

        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNavigation)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment)
                R.id.transactions -> replaceFragment(TransactionFragment)
                R.id.profile -> replaceFragment(ProfileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}