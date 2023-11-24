package com.example.billsmanagement.ui
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.billsmanagement.R
import com.example.billsmanagement.model.Bill

class BillSaveAdapter (context: Context, resource: Int, bills: List<Bill>) :
    ArrayAdapter<Bill>(context, resource, bills) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val bill = getItem(position)
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.bills_list_item, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.bind(bill)
        return itemView
    }

    class ViewHolder(itemView: View) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        private val tvDueDate: TextView = itemView.findViewById(R.id.tvDue)
        private val tvFrequency: TextView = itemView.findViewById(R.id.tvFrequency)
        private val tvBillId: TextView = itemView.findViewById(R.id.tvBill)
        private val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)

        @SuppressLint("SetTextI18n")
        fun bind(bill: Bill?) {
            if (bill != null) {
                tvName.text = "Name: ${bill.name}"
                tvAmount.text = "Amount: ${bill.amount}"
                tvDueDate.text = "Due Date: ${bill.dueDate}"
                tvFrequency.text = "Frequency: ${bill.frequency}"
                tvBillId.text = "BillId: ${bill.billId}"
                tvUserId.text = "UserId: ${bill.userId}"
            }
        }
    }
}