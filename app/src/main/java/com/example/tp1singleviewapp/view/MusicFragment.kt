package com.example.tp1singleviewapp.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp1singleviewapp.controller.MusicAdapter
import com.example.tp1singleviewapp.databinding.FragmentMusicBinding
import com.example.tp1singleviewapp.model.Music
import com.example.tp1singleviewapp.viewModel.MusicViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MusicFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null

    private val binding get() = _binding!!
    private lateinit var userAdapter: MusicAdapter

    private lateinit var concertsList: ArrayList<Music>

    private var searchJob: Job? = null // To manage the debounce logic
    var isSearching = false

    private val viewModel: MusicViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization(view)
        autoSearch(view)
        onCLicks(view)
    }

    fun initialization(view: View) {
        concertsList =  loadConcerts(view.context).toCollection(
            ArrayList()
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        userAdapter = MusicAdapter(concertsList)
        binding.recyclerView.adapter = userAdapter
    }

    fun loadConcerts(context: Context): List<Music> {
        // Read JSON text from assets
        val json = context.assets.open("concerts.json")
            .bufferedReader()
            .use { it.readText() }

        // Parse into a list
        val listType = object : TypeToken<List<Music>>() {}.type
        return Gson().fromJson(json, listType)
    }

    fun onCLicks(view: View) {
        binding.searchIcon.setOnClickListener {
            if (binding.searchEditText.text.isEmpty()) {
                Toast.makeText(
                    view.context,
                    "Recherche vide!",
                    Toast.LENGTH_SHORT
                ).show()
                isSearching = false
                refreshRecyclerView(loadConcerts(view.context).toCollection(
                        ArrayList()
                    )
                )
            } else {
                isSearching = true
                searchJob?.cancel()
                Search(view, binding.searchEditText.text.toString().trim().toLowerCase())
            }
        }
    }

    private fun autoSearch(view: View) {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // toggle clear button
                binding.clearicon.visibility = if (s?.trim().isNullOrEmpty()) View.GONE else View.VISIBLE

                // cancel previous search job
                searchJob?.cancel()

                // launch new coroutine safely scoped to Fragment lifecycle
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    delay(1000) // debounce
                    if (s.isNullOrBlank()) {
                        isSearching = false
                        refreshRecyclerView(loadConcerts(view.context).toCollection(
                                ArrayList()
                            )
                        )
                    } else {
                        isSearching = true
                        Search(view, s.toString().trim().lowercase())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.clearicon.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    fun Search(view: View, text: String) {
        val (searchList, _) = loadConcerts(view.context).toCollection(
            ArrayList()
        ).partition {
            it.group.toLowerCase().contains(text.toLowerCase()) ||
            it.date.toLowerCase().contains(text.toLowerCase()) ||
            it.nationality.toLowerCase().contains(text.toLowerCase()) ||
            it.ticketsLeft.toLowerCase().contains(text.toLowerCase()) ||
            it.price.toLowerCase().contains(text.toLowerCase()) ||
            it.location.toLowerCase().contains(text.toLowerCase())
        }
        refreshRecyclerView(searchList)
    }

    fun refreshRecyclerView(list: List<Music>) {
        concertsList.clear()
        concertsList.addAll(list)
        userAdapter.notifyDataSetChanged()
    }


}