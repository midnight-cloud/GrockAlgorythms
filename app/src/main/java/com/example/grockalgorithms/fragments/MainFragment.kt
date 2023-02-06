package com.example.grockalgorithms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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
            val id = R.array.binary_search
            nextFragment(id)
        }
        binding.rvAlgs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAlgs.adapter = itemAdapter
    }

    private fun nextFragment(id: Int) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_place_main, DetailFragment.newInstance(id))
            addToBackStack("main")
        }
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