package com.example.billsmanagement.workmanager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.billsmanagement.repository.BillsRepository
class DataSyncWorker(context: Context,workerParams: WorkerParameters) :
    CoroutineWorker(context,workerParams){
    val billsRepo = BillsRepository()
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        billsRepo.syncBills()
        billsRepo.syncUpcomingBills()
        return Result.success()
    }
}









