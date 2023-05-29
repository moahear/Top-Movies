package com.gamil.moahear.topmovies.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import com.gamil.moahear.topmovies.R
import com.gamil.moahear.topmovies.databinding.FragmentSplashBinding
import com.gamil.moahear.topmovies.utils.UserInfoDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    //private val userToken = stringPreferencesKey(Constants.USER_TOKEN)

    @Inject
    lateinit var userInfoDataStore: UserInfoDataStore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //check user token
        lifecycle.coroutineScope.launch {
            userInfoDataStore.getUserToken().flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect {
                    delay(2000)
                    if (it.isEmpty()) {
                        //Go to register fragment
                        findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
                    } else {
                        //Go to home fragment
                        findNavController().navigate(R.id.action_to_homeFragment)
                    }
                }
            //region second way with repeatOnLifecycle
            /* viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                 userInfoDataStore.getUserInfo().collect {
                     delay(2000)
                     if (it[userToken].isNullOrEmpty()) {
                         //Go to register fragment
                         findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
                     } else {
                         //Go to home fragment
                         findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                     }
                 }
             }*/
            //endregion
        }
    }
}