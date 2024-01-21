package com.kotlin.practice2.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kotlin.practice2.db.entity.SelectedCoinPriceEntity
import com.kotlin.practice2.network.model.RecentCoinPriceList
import com.kotlin.practice2.repository.DBRepository
import com.kotlin.practice2.repository.NetWorkRepository
import java.util.Calendar
import java.util.Date

class GetCoinPriceRecentContractedWorkManager(val context : Context, workerParameters: WorkerParameters)
    : CoroutineWorker(context, workerParameters)  {

    private val dbRepository = DBRepository()
    private val netWorkRepository = NetWorkRepository()

    override suspend fun doWork(): Result {

        getAllInterestSelectedCoinData()

        return Result.success()
    }

    private suspend fun getAllInterestSelectedCoinData(){
        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        val timeStamp = Calendar.getInstance().time

        for(coinData in selectedCoinList){

            val recentCoinPriceList = netWorkRepository.getInterestCoinPriceDataList(coinData.coin_name)
            saveSelectedCoinPrice(coinData.coin_name, recentCoinPriceList, timeStamp)
        }
    }

    private fun saveSelectedCoinPrice(coinName : String, recentCoinPriceList: RecentCoinPriceList, timeStamp : Date){
        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_date,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timeStamp
        )

        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)

    }


}