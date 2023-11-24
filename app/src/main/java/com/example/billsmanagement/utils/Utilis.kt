package com.example.billsmanagement.utils

import android.view.View
import java.text.DecimalFormat

class Utilis {
    companion object {
        fun formatCurrency(amount: Double): String {
            val fmt = DecimalFormat("KES #,###")
            return fmt.format(amount)

        }

        fun View.show() {
            this.visibility = View.VISIBLE
        }

        fun View.hide() {
            this.visibility = View.GONE
        }
    }
}