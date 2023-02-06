package com.example.grockalgorithms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grockalgorithms.R
import com.example.grockalgorithms.adapters.ItemAdapter
import com.example.grockalgorithms.databinding.FragmentMainBinding
import com.example.grockalgorithms.models.Algorithm

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val algList = mutableListOf<Algorithm>()
    private val itemAdapter = ItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillAlgList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        itemAdapter.submitList(algList)
        itemAdapter.onItemClickListener = {
            Toast.makeText(requireContext(), "Clicked: ${it.title}", Toast.LENGTH_SHORT).show()
        }
        binding.rvAlgs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAlgs.adapter = itemAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillAlgList() {
        val binaryAlg = resources.getStringArray(R.array.binary_search)
        algList.add(
            Algorithm(
                binaryAlg[0].toInt(), R.drawable.icon_alg, binaryAlg[2], binaryAlg[3], binaryAlg[4]
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment()
    }
}