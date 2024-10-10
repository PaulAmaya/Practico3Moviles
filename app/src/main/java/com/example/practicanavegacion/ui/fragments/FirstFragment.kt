package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.practicanavegacion.R
import com.example.practicanavegacion.ui.Person
import com.example.practicanavegacion.ui.adapters.ImagePagerAdapter
import com.example.practicanavegacion.ui.viewModels.PersonViewModel

class FirstFragment : Fragment() {
    private val personViewModel: PersonViewModel by activityViewModels()
    private lateinit var btnGoToSecondFragment: Button
    private lateinit var btnDislike: Button
    private lateinit var btnLike: Button
    private lateinit var viewPager: ViewPager2
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ImagePagerAdapter
    private var currentPersonIndex = 0
    private lateinit var personas: List<Person>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        btnDislike = view.findViewById(R.id.btnDislike)
        btnGoToSecondFragment = view.findViewById(R.id.btnGoToSecondFragment)
        btnLike = view.findViewById(R.id.btnLike)
        viewPager = view.findViewById(R.id.viewPager)
        progressBar = view.findViewById(R.id.progressBar)
        personas = personViewModel.getUnlikedPersons()
        setupViewPager()
        setupEventListeners()
        return view
    }

    private fun setupViewPager() {
        if (personas.isNotEmpty()) {
            adapter = ImagePagerAdapter(requireContext(), personas[currentPersonIndex])
            viewPager.adapter = adapter
            progressBar.max = personas[currentPersonIndex].imageResourceNames.size
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    progressBar.progress = position + 1
                }
            })
        }
    }

    private fun setupEventListeners() {
        btnGoToSecondFragment.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }

        btnDislike.setOnClickListener {
            currentPersonIndex = (currentPersonIndex + 1) % personas.size
            adapter.updatePerson(personas[currentPersonIndex])
            viewPager.setCurrentItem(0, false)
            progressBar.max = personas[currentPersonIndex].imageResourceNames.size
            progressBar.progress = 1
        }

        btnLike.setOnClickListener {
            val currentPerson = personas[currentPersonIndex]
            personViewModel.addLikedPerson(currentPerson)
            personas = personViewModel.getUnlikedPersons()
            currentPersonIndex = 0
            if (personas.isNotEmpty()) {
                adapter.updatePerson(personas[currentPersonIndex])
                viewPager.setCurrentItem(0, false)
                progressBar.max = personas[currentPersonIndex].imageResourceNames.size
                progressBar.progress = 1
            } else {
                // show a message
                Toast.makeText(requireContext(), "Ya no hay personas para dar likes", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}