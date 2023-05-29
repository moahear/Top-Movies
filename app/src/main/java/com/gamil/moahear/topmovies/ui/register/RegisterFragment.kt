package com.gamil.moahear.topmovies.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import com.gamil.moahear.topmovies.R
import com.gamil.moahear.topmovies.databinding.FragmentRegisterBinding
import com.gamil.moahear.topmovies.model.register.RequestRegisterBody
import com.gamil.moahear.topmovies.utils.UserInfoDataStore
import com.gamil.moahear.topmovies.utils.setVisible
import com.gamil.moahear.topmovies.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    @Inject
    lateinit var bodyRegister: RequestRegisterBody

    private val registerViewModel: RegisterViewModel by viewModels()
    @Inject
    lateinit var userInfoDataStore: UserInfoDataStore
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel.registerUser.observe(viewLifecycleOwner) {
         lifecycle.coroutineScope.launch {
             repeatOnLifecycle(Lifecycle.State.CREATED){
                 it.name?.let {name->
                     userInfoDataStore.saveUserInfo(name)
                 }
             }
         }
        }
        registerViewModel.isLoading.observe(viewLifecycleOwner){
            if (it){
                binding.progressSubmit.setVisible(true)
                binding.btnSubmit.setVisible(false)
            }
            else{
                binding.progressSubmit.visibility = View.GONE
                binding.btnSubmit.setVisible(true)
            }
        }
        binding.apply {
            btnSubmit.setOnClickListener {
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    bodyRegister.name = name
                    bodyRegister.password = password
                    bodyRegister.email = email
                    registerViewModel.sendRegisterUser(bodyRegister)
                } else {
                    Snackbar.make(it, getString(R.string.fill_all_fields), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

}