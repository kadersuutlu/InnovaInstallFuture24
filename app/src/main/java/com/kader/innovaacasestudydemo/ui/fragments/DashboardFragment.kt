package com.kader.innovaacasestudydemo.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kader.innovaacasestudydemo.R
import com.kader.innovaacasestudydemo.data.model.Transaction
import com.kader.innovaacasestudydemo.databinding.FragmentDashboardBinding
import com.kader.innovaacasestudydemo.ui.adapter.DashboardAdapter
import com.kader.innovaacasestudydemo.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: DashboardAdapter

    override fun initView() {
        auth = FirebaseAuth.getInstance()

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addTransactionFragment)
        }

        binding.signOutButton.setOnClickListener {
            viewModel.signOut(auth)
            findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        viewModel.fetchTransactions()

        viewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            setupRecyclerView(transactions)
        }
    }

    private fun setupRecyclerView(transactions: List<Transaction>) {
        adapter = DashboardAdapter(transactions)
        binding.recyclerview.adapter = adapter
    }

    override val viewModel by viewModels<DashboardViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

}