package com.kotlin.practice2.network.model

import com.kotlin.practice2.dataModel.RecentPriceData

data class RecentCoinPriceList(
    val status: String,
    val data : List<RecentPriceData>
)
