package com.kotlin.practice2.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.practice2.db.entity.InterestCoinEntity
import com.kotlin.practice2.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val dbRepository = DBRepository()

    lateinit var selectedCoinList : LiveData<List<InterestCoinEntity>>

    fun getAllInterestedCoin() = viewModelScope.launch {
        val coinList = dbRepository.getAllInterestCoinData().asLiveData()
        selectedCoinList = coinList
    }

    fun updateInterestCoin(interestCoinEntity: InterestCoinEntity) = viewModelScope.launch(Dispatchers.IO) {
        interestCoinEntity.selected = !interestCoinEntity.selected
        dbRepository.updateInterestCoinData(interestCoinEntity)
    }

}