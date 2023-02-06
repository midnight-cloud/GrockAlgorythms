package com.example.grockalgorithms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.grockalgorithms.R
import com.example.grockalgorithms.databinding.FragmentDetailBinding
import com.example.grockalgorithms.models.Algorithm

private const val ARG_PARAM1 = "param1"

class DetailFragment : Fragment() {
    private var algType: Int? = null

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            algType = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment(algType!!)

        binding.fbtnCalculate.setOnClickListener {
            startCalcFragment(algType!!)
        }
    }

    private fun startCalcFragment(id: Int) {
        parentFragmentManager.commit {
            when(id) {
                0 -> replace(R.id.fragment_place_main, BinarySearchFragment.newInstance())
            }
            setReorderingAllowed(true)
            addToBackStack("detail")
        }


    }

    private fun initFragment(id: Int) {
        val alg = Algorithm.list[id]
        binding.tvAlgTitle.text = alg.title
        binding.tvAlgDifficult.text = "Сложность: ${alg.difficult}"
        binding.tvAlgDescription.text = alg.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(algType: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, algType)
                }
            }
    }
}