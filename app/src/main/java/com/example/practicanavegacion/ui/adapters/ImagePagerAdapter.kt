package com.example.practicanavegacion.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicanavegacion.R
import com.example.practicanavegacion.ui.Person

class ImagePagerAdapter(private val context: Context, private var person: Person) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_page_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageResId = getImageResourceByName(context, person.imageResourceNames[position])
        holder.imageView.setImageResource(imageResId)
        holder.nameTextView.text = person.name

    }

    override fun getItemCount(): Int {
        return person.imageResourceNames.size
    }

    fun updatePerson(newPerson: Person) {
        person = newPerson

        notifyDataSetChanged()
    }

    private fun getImageResourceByName(context: Context, resourceName: String): Int {
        return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
}