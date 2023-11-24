package com.example.billsmanagement.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.billsmanagement.repository.BillsRepository

class UpcomingBillsWorker(context: Context,workerParams:WorkerParameters):CoroutineWorker(context,workerParams) {
    val billsRepository=BillsRepository()
    override suspend fun doWork(): Result {
      billsRepository.createRecurringWeeklyBills()
      billsRepository.createRecurringMonthlyBills()
      billsRepository.createRecurringAnnualBills()
        return Result.success()
    }
}

