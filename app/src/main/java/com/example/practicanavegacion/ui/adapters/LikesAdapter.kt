package com.example.practicanavegacion.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicanavegacion.ui.Person
import com.example.practicanavegacion.databinding.LikedPersonItemBinding

class LikesAdapter(private val likedPeople: List<Person>) :
    RecyclerView.Adapter<LikesAdapter.LikesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        val binding = LikedPersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LikesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        val person = likedPeople[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = likedPeople.size

    class LikesViewHolder(private val binding: LikedPersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.nameTextView.text = person.name
            if (person.imageResourceNames.isNotEmpty()) {
                val imageResId = binding.root.context.resources.getIdentifier(
                    person.imageResourceNames[0], "drawable", binding.root.context.packageName)
                binding.personImage.setImageResource(imageResId)
            }
        }
    }
}