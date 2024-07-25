package com.kader.innovaacasestudydemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kader.innovaacasestudydemo.data.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>> get() = _transactions

    fun fetchTransactions() {
        firestore.collection("transactions")
            .get()
            .addOnSuccessListener { result ->
                val transactionList = mutableListOf<Transaction>()
                for (document in result) {
                    val transaction = document.toObject(Transaction::class.java)
                    transactionList.add(transaction.copy(id = document.id))
                }
                _transactions.postValue(transactionList)
            }
            .addOnFailureListener {
                //error message
            }
    }

    fun signOut(auth: FirebaseAuth) {
        auth.signOut()
    }
}