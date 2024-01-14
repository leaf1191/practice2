package com.kotlin.practice2.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.practice2.R
import com.kotlin.practice2.databinding.FragmentCoinListBinding
import com.kotlin.practice2.db.entity.InterestCoinEntity
import com.kotlin.practice2.view.adapter.CoinListRVAdapter

class CoinListFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private val selectedList = ArrayList<InterestCoinEntity>()
    private val unSelectedList = ArrayList<InterestCoinEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllInterestedCoin()
        viewModel.selectedCoinList.observe(viewLifecycleOwner){

            selectedList.clear()
            unSelectedList.clear()

            for(item in it){
                if(item.selected){
                    selectedList.add(item)
                } else{
                    unSelectedList.add(item)
                }
            }

            setSelectedListRV()

        }
    }

    fun setSelectedListRV(){
        val selectedRVAdapter = CoinListRVAdapter(requireContext(), selectedList)
        binding.selectedCoinRV.adapter = selectedRVAdapter
        binding.selectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        selectedRVAdapter.itemClick = object : CoinListRVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                viewModel.updateInterestCoin(selectedList[position])
            }

        }

        val unSelectedRVAdapter = CoinListRVAdapter(requireContext(), unSelectedList)
        binding.unSelectedCoinRV.adapter = unSelectedRVAdapter
        binding.unSelectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        unSelectedRVAdapter.itemClick = object : CoinListRVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                viewModel.updateInterestCoin(unSelectedList[position])
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}