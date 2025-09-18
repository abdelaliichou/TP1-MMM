package com.example.tp1singleviewapp.model

import android.os.Parcelable
import com.example.tp1singleviewapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music (
    val name: String,
    val vues: String,
    val author: String,
    val image: String,
): Parcelable {
    companion object {
        fun getMusicList() : List<Music> {
            return listOf(
                Music(
                    name = "Blinding Lights",
                    vues = "1.2B views",
                    author = "The Weeknd",
                    image = "http://images.unsplash.com/photo-1614613535308-eb5fbd3d2c17?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Shape of You",
                    vues = "3.5B views",
                    author = "Ed Sheeran",
                    image = "https://images.unsplash.com/photo-1696488567389-e582d3ba3f19?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Levitating",
                    vues = "950M views",
                    author = "Dua Lipa",
                    image = "https://images.unsplash.com/photo-1505672984986-b7c468c7a134?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Uptown Funk",
                    vues = "4.1B views",
                    author = "Bruno Mars",
                    image = "https://images.unsplash.com/photo-1468164016595-6108e4c60c8b?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Someone Like You",
                    vues = "2.9B views",
                    author = "Adele",
                    image = "https://images.unsplash.com/photo-1741615331281-104e2baa2393?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Believer",
                    vues = "1.8B views",
                    author = "Imagine Dragons",
                    image = "https://images.unsplash.com/photo-1740710928425-7efb1273488a?q=80&w=1074&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "HUMBLE.",
                    vues = "1.4B views",
                    author = "Kendrick Lamar",
                    image = "https://images.unsplash.com/photo-1552267094-b4c3771bd5e4?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Señorita",
                    vues = "1.6B views",
                    author = "Shawn Mendes & Camila Cabello",
                    image = "https://images.unsplash.com/photo-1659545730194-cbdb852f299d?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Rolling in the Deep",
                    vues = "2.3B views",
                    author = "Adele",
                    image = "https://images.unsplash.com/photo-1496957961599-e35b69ef5d7c?q=80&w=1172&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                Music(
                    name = "Bad Guy",
                    vues = "1.9B views",
                    author = "Billie Eilish",
                    image = "https://plus.unsplash.com/premium_photo-1682125554685-2e52be3112db?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )
            )
        }
    }
}

