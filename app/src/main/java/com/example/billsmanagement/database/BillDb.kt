package com.example.billsmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.billsmanagement.model.Bill
import com.example.billsmanagement.model.UpcomingBill

@Database(entities = [Bill::class,UpcomingBill::class], version =5)
abstract class BillDb:RoomDatabase() {
    abstract fun billDao():BillDao

//    Adding tables to the database with
    abstract fun upcomingBillsDao():UpcomingBillsDao
            companion object{
                var database:BillDb?=null
                fun getDatabase(context: Context):BillDb{
                    if (database==null){
                        database=Room
                            .databaseBuilder(context, BillDb::class.java,"BillzDb")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    return database as BillDb
                }

            }
}