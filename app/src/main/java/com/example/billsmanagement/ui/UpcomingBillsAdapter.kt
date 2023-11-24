package com.example.billsmanagement.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.billsmanagement.databinding.UpcomingBillsListItemBinding
import com.example.billsmanagement.model.UpcomingBill
import com.example.billsmanagement.utils.DateTimeUtils

class UpcomingBillsAdapter(var upcomingBill: List<UpcomingBill>,var onClickBill: OnClickBill):Adapter<UpcomingBillsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingBillsViewHolder {
    val binding= UpcomingBillsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpcomingBillsViewHolder(binding)

    }
    override fun onBindViewHolder(holder: UpcomingBillsViewHolder, position: Int) {
      val upcomingBill=upcomingBill.get(position)
        holder.binding.apply {
            cbUpcoming.isChecked=upcomingBill.paid
            cbUpcoming.text=upcomingBill.name
            tvAmount.text=upcomingBill.amount.toString()
            tvDueDate.text=DateTimeUtils.formatDateReadable(upcomingBill.dueDate)
        }
        holder.binding.cbUpcoming.setOnClickListener {
            onClickBill.onCheckBoxMarked(upcomingBill)
        }
    }
    override fun getItemCount(): Int {
    return upcomingBill.size
    }
}
class UpcomingBillsViewHolder(var binding: UpcomingBillsListItemBinding):ViewHolder(binding.root){

}interface OnClickBill{
    fun onCheckBoxMarked(upcomingBill: UpcomingBill)
}
