package com.kader.innovaacasestudydemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kader.innovaacasestudydemo.R
import com.kader.innovaacasestudydemo.data.model.Transaction

class DashboardAdapter(
    private val transactions: List<Transaction>
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    inner class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val type: TextView = itemView.findViewById(R.id.type)
        val amount: TextView = itemView.findViewById(R.id.amount)
        val date: TextView = itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return DashboardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.title.text = transaction.title
        holder.type.text = transaction.transactionType
        holder.amount.text = transaction.amount
        holder.date.text = transaction.date
    }

    override fun getItemCount(): Int = transactions.size
}
