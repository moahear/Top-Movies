package com.gamil.moahear.topmovies.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gamil.moahear.topmovies.R
import com.gamil.moahear.topmovies.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding:FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }
}