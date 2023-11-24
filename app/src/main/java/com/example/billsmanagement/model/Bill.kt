package com.example.billsmanagement.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Bills",
    indices=[Index(value=["name"],unique=true)])
data class Bill(
   @SerializedName("bill_id") @PrimaryKey
    @Expose var billId:String,
    @Expose var name:String,
    @Expose var amount: Double,
    @Expose var frequency:String,
    @Expose @SerializedName("due_date")var dueDate:String,
    @Expose @SerializedName("user_id")var userId:String,
//    @Expose(serialize = false)
   var synced:Boolean
)
