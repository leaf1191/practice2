package com.kotlin.practice2.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.practice2.R
import com.kotlin.practice2.databinding.FragmentPriceChangeBinding
import com.kotlin.practice2.view.adapter.PriceListUpDownRVAdapter


class PriceChangeFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentPriceChangeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPriceChangeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllSelectedCoinData()

        viewModel.arr15min.observe(viewLifecycleOwner){
            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(), it)
            binding.price15m.adapter = priceListUpDownRVAdapter
            binding.price15m.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.arr30min.observe(viewLifecycleOwner){
            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(), it)
            binding.price30m.adapter = priceListUpDownRVAdapter
            binding.price30m.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.arr45min.observe(viewLifecycleOwner){
            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext(), it)
            binding.price45m.adapter = priceListUpDownRVAdapter
            binding.price45m.layoutManager = LinearLayoutManager(requireContext())
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}