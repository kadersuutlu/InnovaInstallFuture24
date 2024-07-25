package com.kader.innovaacasestudydemo.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kader.innovaacasestudydemo.R
import com.kader.innovaacasestudydemo.databinding.FragmentLoginBinding
import com.kader.innovaacasestudydemo.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun initView() {
        auth = FirebaseAuth.getInstance()

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        val savedEmail = sharedPreferences.getString("email", "")
        if (!savedEmail.isNullOrEmpty()) {
            binding.mailAddress.setText(savedEmail)
            binding.rememberMe.isChecked = true
        }

        binding.loginButton.setOnClickListener {
            val email = binding.mailAddress.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (binding.rememberMe.isChecked) {
                    saveEmail(email)
                } else {
                    clearEmail()
                }
                loginUser(email, password)
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveEmail(email: String) {
        with(sharedPreferences.edit()) {
            putString("email", email)
            apply()
        }
    }

    private fun clearEmail() {
        with(sharedPreferences.edit()) {
            remove("email")
            apply()
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
        } else {
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    override val viewModel by viewModels<LoginViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
}