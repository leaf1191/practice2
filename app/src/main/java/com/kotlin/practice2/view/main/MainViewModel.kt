package com.kotlin.practice2.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.practice2.dataModel.UpDownDataSet
import com.kotlin.practice2.db.entity.InterestCoinEntity
import com.kotlin.practice2.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()

    lateinit var selectedCoinList : LiveData<List<InterestCoinEntity>>

    private var _arr15min = MutableLiveData<List<UpDownDataSet>>()
    val arr15min : LiveData<List<UpDownDataSet>> get() = _arr15min

    private var _arr30min = MutableLiveData<List<UpDownDataSet>>()
    val arr30min : LiveData<List<UpDownDataSet>> get() = _arr30min

    private var _arr45min = MutableLiveData<List<UpDownDataSet>>()
    val arr45min : LiveData<List<UpDownDataSet>> get() = _arr45min


    fun getAllInterestedCoin() = viewModelScope.launch {
        val coinList = dbRepository.getAllInterestCoinData().asLiveData()
        selectedCoinList = coinList
    }

    fun updateInterestCoin(interestCoinEntity: InterestCoinEntity) = viewModelScope.launch(Dispatchers.IO) {
        interestCoinEntity.selected = !interestCoinEntity.selected
        dbRepository.updateInterestCoinData(interestCoinEntity)
    }


    fun getAllSelectedCoinData() = viewModelScope.launch(Dispatchers.IO) {

        var selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        val arr15min = ArrayList<UpDownDataSet>()
        val arr30min = ArrayList<UpDownDataSet>()
        val arr45min = ArrayList<UpDownDataSet>()

        for(data in selectedCoinList){
            val coinName = data.coin_name
            val oneCoinData = dbRepository.getOneSelectedCoinData(coinName).reversed()

            val size = oneCoinData.size

            if(size > 1){
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[1].price.toDouble()
                val upDownDataSet = UpDownDataSet(coinName, changedPrice.toString())
                arr15min.add(upDownDataSet)
            }

            if(size > 2){
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[2].price.toDouble()
                val upDownDataSet = UpDownDataSet(coinName, changedPrice.toString())
                arr30min.add(upDownDataSet)
            }

            if(size > 3){
                val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[3].price.toDouble()
                val upDownDataSet = UpDownDataSet(coinName, changedPrice.toString())
                arr45min.add(upDownDataSet)
            }

            withContext(Dispatchers.Main) {
                _arr15min.value = arr15min
                _arr30min.value = arr30min
                _arr45min.value = arr45min
            }

        }

    }

}