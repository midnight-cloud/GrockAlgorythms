package com.example.grockalgorithms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.grockalgorithms.databinding.ActivityMainBinding
import com.example.grockalgorithms.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_place_main, MainFragment.newInstance())
            }
        } else {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_place_main, MainFragment.newInstance())
            }
        }
    }
}