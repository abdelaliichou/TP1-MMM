package com.example.tp1singleviewapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1singleviewapp.R
import com.example.tp1singleviewapp.model.Music
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class MusicAdapter(private val musicList: List<Music>) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameMUSIC: TextView = view.findViewById(R.id.nameMUSIC)
        val authorMUSIC: TextView = view.findViewById(R.id.authorMUSIC)
        val vueMUSIC: TextView = view.findViewById(R.id.vuesMUSIC)
        val imageMUSIC: ImageView = view.findViewById(R.id.imageMUSIC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.music_layout,
                parent,
                false
        )
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val user = musicList[position]
        holder.nameMUSIC.text = user.name
        holder.authorMUSIC.text = user.author
        holder.vueMUSIC.text = user.vues
        Picasso.get()
            .load(user.image)
            .into(holder.imageMUSIC)
    }

    override fun getItemCount() = musicList.size
}
