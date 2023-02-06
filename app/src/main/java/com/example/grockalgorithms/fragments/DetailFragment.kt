package com.example.grockalgorithms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.grockalgorithms.databinding.FragmentDetailBinding

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
    }

    private fun initFragment(id: Int) {
        val alg = resources.getStringArray(id)
        binding.tvAlgTitle.text = alg[2]
        binding.tvAlgDifficult.text = "Сложность: ${alg[3]}"
        binding.tvAlgDescription.text = alg[4]
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