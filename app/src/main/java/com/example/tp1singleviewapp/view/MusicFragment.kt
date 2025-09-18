package com.example.tp1singleviewapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1singleviewapp.R
import com.example.tp1singleviewapp.controller.MusicAdapter
import com.example.tp1singleviewapp.model.Music
import com.example.tp1singleviewapp.viewModel.MusicViewModel

class MusicFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: MusicAdapter

    private val viewModel: MusicViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_music,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialization(view)
        super.onViewCreated(view, savedInstanceState)
    }

    fun initialization(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        userAdapter = MusicAdapter(Music.getMusicList())
        recyclerView.adapter = userAdapter
    }
}