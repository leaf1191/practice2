package com.kotlin.practice2.repository

import com.kotlin.practice2.network.Api
import com.kotlin.practice2.network.RetrofitInstance
import retrofit2.create

class NetWorkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

    suspend fun getInterestCoinPriceDataList(coin : String) = client.getRecentCoinPrice(coin)
}