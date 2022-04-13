package com.example.onlineshop.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.onlineshop.R
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var homeFragment = HomeFragment.newInstance()
    private var favouriteFragment = FavouriteFragment.newInstance()
    private var cartFragment = CartFragment.newInstance()
    private var profileFragment = ProfileFragment.newInstance()
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, homeFragment, homeFragment.tag).hide(homeFragment)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, favouriteFragment, favouriteFragment.tag).hide(favouriteFragment)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, cartFragment, cartFragment.tag).hide(cartFragment).commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, profileFragment, profileFragment.tag).hide(profileFragment)
            .commit()

        supportFragmentManager.beginTransaction().show(activeFragment).commit()

        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                    activeFragment = homeFragment
                }
                R.id.action_cart -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(cartFragment).commit()
                    activeFragment = cartFragment
                }
                R.id.action_starred -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(favouriteFragment).commit()
                    activeFragment = favouriteFragment
                }
                R.id.action_account -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
                    activeFragment = profileFragment
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


    }


}