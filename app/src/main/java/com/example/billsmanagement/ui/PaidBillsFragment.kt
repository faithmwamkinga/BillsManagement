package com.example.billsmanagement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.billsmanagement.R
import com.example.billsmanagement.databinding.FragmentPaidBillsBinding
import com.example.billsmanagement.model.UpcomingBill
import com.example.billsmanagement.viewmodel.BillsViewModel


class PaidBillsFragment : Fragment(),OnClickBill {
val billsViewModel:BillsViewModel by viewModels()
    var binding:FragmentPaidBillsBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
 binding=FragmentPaidBillsBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        billsViewModel.getPaidBills().observe(this){paidBills->
            val adapter=UpcomingBillsAdapter(paidBills,this)
            binding?.rvPaidBills?.layoutManager=LinearLayoutManager(requireContext())
            binding?.rvPaidBills?.adapter=adapter

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    override fun onCheckBoxMarked(upcomingBill: UpcomingBill) {
        upcomingBill.paid=!upcomingBill.paid
        upcomingBill.synced=false
        billsViewModel.updateUpcomingBill(upcomingBill)

    }
}