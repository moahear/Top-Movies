package com.gamil.moahear.topmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gamil.moahear.topmovies.R
import com.gamil.moahear.topmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding:ActivityMainBinding
    //NavController
    private val navController by lazy{
        binding.fragmentContainerView.getFragment<NavHostFragment>().navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            bottomNavigationView.setupWithNavController(navController)
            //show or hide bottom navigation on fragment
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id==R.id.splashFragment){
                    bottomNavigationView.visibility = View.GONE
                }
                else{
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }

        }

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onNavigateUp()
    }
}