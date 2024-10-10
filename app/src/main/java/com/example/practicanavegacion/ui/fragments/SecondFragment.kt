package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicanavegacion.R
import com.example.practicanavegacion.ui.adapters.LikesAdapter
import com.example.practicanavegacion.ui.viewModels.PersonViewModel

class SecondFragment : Fragment() {
    private val personViewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        personViewModel.likedPersons.observe(viewLifecycleOwner, Observer { likedPersons ->
            recyclerView.adapter = LikesAdapter(likedPersons)
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SecondFragment()
    }
}