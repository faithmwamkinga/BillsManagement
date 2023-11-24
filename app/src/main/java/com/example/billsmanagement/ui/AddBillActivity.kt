package com.example.billsmanagement.ui

//noinspection SuspiciousImport
import android.R
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.billsmanagement.databinding.ActivityAddBillBinding
import com.example.billsmanagement.model.Bill
import com.example.billsmanagement.utils.Constants
import com.example.billsmanagement.utils.DateTimeUtils
import com.example.billsmanagement.viewmodel.BillsViewModel
import java.util.Calendar
import java.util.UUID


class AddBillActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBillBinding
    val billsViewModel: BillsViewModel by viewModels()
    var selectedDate = 0
    var selectedMonth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupFreqSpinner()
        setupDpDueDate()
        binding.btnSaveBill.setOnClickListener {
            validateBill()
        }
    }

    fun setupFreqSpinner() {
        val frequencies = arrayOf(Constants.WEEKLY, Constants.MONTHLY, Constants.ANNUAL)
        val freqAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, frequencies)
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter = freqAdapter

        binding.spFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (binding.spFrequency.selectedItem.toString()) {
                    Constants.WEEKLY -> {
                        showSpinner()
                        setUpDueDateSpinner(Array(31) { it + 1 })
                    }

                        Constants.MONTHLY -> {
                    showSpinner()
                    setUpDueDateSpinner(Array(31) { it + 1 })
                }

                        Constants.ANNUAL -> {
                    showDatePicker()
                    setupDpDueDate()
                }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    fun showSpinner() {
        binding.dpDueDate.hide()
        binding.spDueDate.show()
    }

    fun showDatePicker() {
        binding.dpDueDate.show()
        binding.spDueDate.hide()
    }

    fun setUpDueDateSpinner(dates: Array<Int>) {
        val dueDateAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDueDate.adapter = dueDateAdapter
    }

    fun setupDpDueDate() {
        val cal = Calendar.getInstance()
        binding.dpDueDate.init(
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ) { _, _, month, date ->
            selectedDate = date
            selectedMonth = month+1
        }
    }

    fun validateBill(){
        val name = binding.etName.text.toString()
        val amount = binding.etAmount.text.toString()
        val frequency = binding.spFrequency.selectedItem.toString()
        val dueDate = if(frequency==Constants.ANNUAL){
            DateTimeUtils
                .createDateFromDayAndMonth(selectedDate,selectedMonth)
                .substring(5)
        } else{
            binding.spDueDate.selectedItem.toString()
        }

        var error = false
        if(name.isBlank()){
            error = true
            binding.etName.error = getString(com.example.billsmanagement.R.string.name_required)
        }

        if(amount.isBlank()){
            error = true
            binding.etName.error =getString(com.example.billsmanagement.R.string.amount_is_required)
        }

        if(!error) {
            val prefs = getSharedPreferences(Constants.PREFS, MODE_PRIVATE)
            val userId = prefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)
            val newBill = Bill(
                name = name,
                amount = amount.toDouble(),
                frequency = frequency,
                dueDate = dueDate,
                billId = UUID.randomUUID().toString(),
                userId = userId.toString(),
                synced=false
            )
            billsViewModel.saveBill(newBill)
            clearForm()
        }
    }
    fun clearForm(){
        binding.etName.setText(Constants.EMPTY_STRING)
        binding.etAmount.setText(Constants.EMPTY_STRING)
        binding.spFrequency.setSelection(0)
        showSpinner()
        binding.spDueDate.setSelection(0)
    }
    fun View.show(){
        this.visibility = View.VISIBLE
    }

    fun View.hide(){
        this.visibility = View.GONE
    }

}

