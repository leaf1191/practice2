package com.kotlin.practice2.repository

import com.kotlin.practice2.App
import com.kotlin.practice2.db.CoinPriceDatabase
import com.kotlin.practice2.db.entity.InterestCoinEntity
import com.kotlin.practice2.db.entity.SelectedCoinPriceEntity

class DBRepository {

    val context = App.context()
    val db = CoinPriceDatabase.getDatabase(context)

    fun getAllInterestCoinData() = db.interestCoinDAO().getAllData()

    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().insert(interestCoinEntity)

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().update(interestCoinEntity)

    fun getAllInterestSelectedCoinData() = db.interestCoinDAO().getSelectedData()


    fun getAllCoinPriceData() = db.selectedCoinDAO().getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity) = db.selectedCoinDAO().insert(selectedCoinPriceEntity)

    fun getOneSelectedCoinData(coinName : String) = db.selectedCoinDAO().getOneCoinData(coinName)


}