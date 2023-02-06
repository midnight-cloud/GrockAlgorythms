package com.example.grockalgorithms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.grockalgorithms.databinding.FragmentBinarySearchBinding
import kotlin.math.ln


class BinarySearchFragment : Fragment() {

    private var _binding: FragmentBinarySearchBinding? = null
    private val binding get() = _binding!!
    private var sortedList = listOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinarySearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnGenerate.setOnClickListener {
            binding.tvOutputList.text = generate()
        }
        binding.btnCalculate.setOnClickListener {
            binding.tvOutputData.text = calculate()

        }
    }

    private fun generate(): String {
        var input: Int? = 0
        try {
            input = binding.etInputData.text.toString().toInt()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Неправильные данные", Toast.LENGTH_SHORT).show()
        }
        sortedList = getRandomList(input!!).sorted()
        return sortedList.toString()
    }

    private fun calculate(): String {
        val findItem: Int?
        val search: Int?
        try {
            findItem = binding.etSearchNumber.text.toString().toInt()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Введите искомое число", Toast.LENGTH_SHORT).show()
            return "Введены неправильные данные!!!"
        }
        try {
            search = binarySearch(sortedList, findItem)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Введите искомое число", Toast.LENGTH_SHORT).show()
            return "Введены неправильные данные!!!"
        }
        val difficulty = log2(sortedList.size)
        return if (search != null) {
                    "Искомое число: ${findItem}\n" +
                    "Сложность (макс шагов): ${difficulty}\n" +
                    "Выполнено за $search шагов"
        } else {
            "Число $findItem не найдено\n"
        }
    }

    private fun getRandomList(size: Int): List<Int> {
        val rndList = mutableListOf<Int>()
        for (i in 0 until size) {
            rndList.add((0..1000).random())
        }
        return rndList
    }

    private fun binarySearch(list: List<Int>, item: Int): Int? {
        var low = 0
        var high = list.size - 1
        var steps = 0
        while (low <= high) {
            steps++
            val mid = (low + high) / 2
            val guess = list[mid]
            if (guess == item)
                return steps
            if (guess > item)
                high = mid - 1
            else
                low = mid + 1
        }
        return null
    }

    private fun log2(n: Int): Double {
        return ln(n.toDouble()) / ln(2.0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BinarySearchFragment()
    }
}