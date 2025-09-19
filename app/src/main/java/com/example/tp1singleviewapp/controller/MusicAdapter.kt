package com.example.tp1singleviewapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1singleviewapp.R
import com.example.tp1singleviewapp.animations.ISTICAnimation.fadeIn
import com.example.tp1singleviewapp.model.Music
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class MusicAdapter(private val musicList: List<Music>) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupMUSIC: TextView = view.findViewById(R.id.groupeMUSIC)
        val dateMUSIC: TextView = view.findViewById(R.id.dateMUSIC)
        val locationMUSIC: TextView = view.findViewById(R.id.locationMUSIC)
        val priceMUSIC: TextView = view.findViewById(R.id.priceMUSIC)
        val ticketsMUSIC: TextView = view.findViewById(R.id.ticketsMUSIC)
        val isFavoriteMUSIC: ImageView = view.findViewById(R.id.favorisMUSIC)
        val isNotFavoriteMUSIC: ImageView = view.findViewById(R.id.notFavorisMUSIC)
        val imageMUSIC: ImageView = view.findViewById(R.id.imageMUSIC)
        val card: MaterialCardView = view.findViewById(R.id.main_card)
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
        val concert = musicList[position]
        holder.groupMUSIC.text = concert.group
        holder.dateMUSIC.text = "${concert.date}, ${concert.location}"
        holder.locationMUSIC.text = concert.nationality
        holder.priceMUSIC.text = concert.price + "€"
        holder.ticketsMUSIC.text = concert.ticketsLeft + " tickets"
        Picasso.get()
            .load(concert.Image)
            .into(holder.imageMUSIC)
        if (concert.favorite) {
            holder.isFavoriteMUSIC.visibility = View.VISIBLE
            holder.isNotFavoriteMUSIC.visibility = View.GONE
        } else {
            holder.isFavoriteMUSIC.visibility = View.GONE
            holder.isNotFavoriteMUSIC.visibility = View.VISIBLE
        }

       holder.card.fadeIn( 0L)
    }

    override fun getItemCount() = musicList.size
}
