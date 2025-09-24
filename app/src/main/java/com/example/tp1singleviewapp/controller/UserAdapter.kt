package com.example.tp1singleviewapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1singleviewapp.R
import com.example.tp1singleviewapp.animations.ISTICAnimation.fadeIn
import com.example.tp1singleviewapp.model.User
import com.example.tp1singleviewapp.room.Concert
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class UserAdapter(var usersList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name_surname_text: TextView = view.findViewById(R.id.name_surname_text)
        val birthday_text: TextView = view.findViewById(R.id.birthday_text)
        val country_text: TextView = view.findViewById(R.id.country_text)
        val number_text: TextView = view.findViewById(R.id.number_text)
        val email_text: TextView = view.findViewById(R.id.email_text)
        val card: MaterialCardView = view.findViewById(R.id.main_card2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.user_layout,
                parent,
                false
        )
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = usersList[position]
        holder.name_surname_text.text = user.name + " " + user.surname
        holder.birthday_text.text = user.birthday
        holder.country_text.text = user.country
        holder.number_text.text = user.number
        holder.email_text.text = user.email
        holder.card.fadeIn( 0L)
    }

    override fun getItemCount() = usersList.size
}
