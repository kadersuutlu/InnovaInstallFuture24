package com.kader.innovaacasestudydemo.ui.fragments


import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.kader.innovaacasestudydemo.databinding.FragmentAddTransactionBinding
import com.kader.innovaacasestudydemo.viewmodel.AddTransactionViewModel
import java.text.SimpleDateFormat
import java.util.Locale


class AddTransactionFragment : BaseFragment<FragmentAddTransactionBinding, AddTransactionViewModel>() {

    private lateinit var firestore: FirebaseFirestore

    override fun initView() {
        firestore = FirebaseFirestore.getInstance()

        val transactionTypes = resources.getStringArray(com.kader.innovaacasestudydemo.R.array.array_transaction_type)
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, transactionTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.transactionType.adapter = adapter

        binding.addButton.setOnClickListener {
            val title = binding.title.text.toString().trim()
            val amount = binding.amount.text.toString().trim()
            val transactionType = binding.transactionType.selectedItem.toString()
            val date = binding.date.text.toString().trim()

            if (title.isNotEmpty() && amount.isNotEmpty() && date.isNotEmpty()) {
                if (isValidDate(date)) {
                    addTransactionToFirestore(title, amount, transactionType, date)
                } else {
                    Toast.makeText(context, "Invalid date format. Please enter the date in dd-MM-yyyy format.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        dateFormat.isLenient = false
        return try {
            dateFormat.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun addTransactionToFirestore(title: String, amount: String, transactionType: String, date: String) {
        val transaction = hashMapOf(
            "title" to title,
            "amount" to amount,
            "transactionType" to transactionType,
            "date" to date
        )

        firestore.collection("transactions")
            .add(transaction)
            .addOnSuccessListener {
                Toast.makeText(context, "Transaction added successfully", Toast.LENGTH_SHORT).show()
                clearFields()
                findNavController().navigate(com.kader.innovaacasestudydemo.R.id.action_addTransactionFragment_to_dashboardFragment)
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Failed to add transaction: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearFields() {
        binding.title.text.clear()
        binding.amount.text.clear()
        binding.transactionType.setSelection(0)
        binding.date.text.clear()
    }

    override val viewModel by viewModels<AddTransactionViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddTransactionBinding =
        FragmentAddTransactionBinding.inflate(inflater, container, false)


}