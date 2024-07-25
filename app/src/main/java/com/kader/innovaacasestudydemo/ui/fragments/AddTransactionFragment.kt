package com.kader.innovaacasestudydemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kader.innovaacasestudydemo.R
import com.kader.innovaacasestudydemo.databinding.FragmentAddTransactionBinding
import com.kader.innovaacasestudydemo.viewmodel.AddTransactionViewModel
import com.kader.innovaacasestudydemo.viewmodel.DashboardViewModel



class AddTransactionFragment : BaseFragment<FragmentAddTransactionBinding, AddTransactionViewModel>() {

    override fun initView() {
        TODO("Not yet implemented")
    }

    override val viewModel by viewModels<AddTransactionViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddTransactionBinding =
        FragmentAddTransactionBinding.inflate(inflater, container, false)


}