package com.example.billsmanagement.model

data class BillsSummary(
    var paid:Double,
    var upcoming:Double,
    var overdue:Double,
    var total:Double,
)
