package com.kader.innovaacasestudydemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kader.innovaacasestudydemo.R
import com.kader.innovaacasestudydemo.databinding.FragmentDashboardBinding
import com.kader.innovaacasestudydemo.viewmodel.DashboardViewModel


class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override fun initView() {
        TODO("Not yet implemented")
    }

    override val viewModel by viewModels<DashboardViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

}