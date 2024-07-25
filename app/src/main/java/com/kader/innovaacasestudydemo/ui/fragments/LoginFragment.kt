package com.kader.innovaacasestudydemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kader.innovaacasestudydemo.R
import com.kader.innovaacasestudydemo.databinding.FragmentLoginBinding
import com.kader.innovaacasestudydemo.viewmodel.LoginViewModel



class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun initView() {
        println(   "Login")
    }

    override val viewModel by viewModels<LoginViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
}