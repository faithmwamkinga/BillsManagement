package com.example.billsmanagement.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.billsmanagement.model.Bill

@Dao
interface BillDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
fun saveBill(bill: Bill)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBills(bill: Bill)
    @Query("SELECT * FROM Bills WHERE frequency=:freq")
    fun getRecurringBills(freq: String): List<Bill>
    @Query("SELECT * FROM Bills ORDER BY dueDate")
    fun getAllBills(): LiveData<List<Bill>>
    @Query("SELECT *FROM Bills WHERE synced=0")
    fun getUnsyncedBills():List<Bill>

}