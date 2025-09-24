package com.example.tp1singleviewapp.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp1singleviewapp.controller.MusicAdapter
import com.example.tp1singleviewapp.databinding.FragmentMusicBinding
import com.example.tp1singleviewapp.model.Music
import com.example.tp1singleviewapp.room.Concert
import com.example.tp1singleviewapp.room.ConcertDao
import com.example.tp1singleviewapp.room.RoomDB
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
    private lateinit var roomConcertsList: ArrayList<Concert>

    private lateinit var musicDao: ConcertDao

    private var searchJob: Job? = null
    var isSearching = false


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
        autoSearch()
        onCLicks(view)
    }

    fun initialization(view: View) {
        musicDao = RoomDB.getDatabase(requireContext()).concertDao()
        roomConcertsList =  loadRoomConcerts().toCollection(ArrayList())
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        userAdapter = MusicAdapter(roomConcertsList)
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

    fun loadRoomConcerts(): List<Concert> {
        return musicDao.getAllConcerts()
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
                refreshRecyclerView(loadRoomConcerts().toCollection(ArrayList()))
            } else {
                isSearching = true
                searchJob?.cancel()
                Search( binding.searchEditText.text.toString().trim().toLowerCase())
            }
        }

        binding.openButton.setOnClickListener {
            val dialog = AddConcertDialog()
            dialog.show(parentFragmentManager, "AddConcertDialog")
        }

    }

    private fun autoSearch() {
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
                        refreshRecyclerView(loadRoomConcerts().toCollection(ArrayList()))
                    } else {
                        isSearching = true
                        Search( s.toString().trim().lowercase())
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.clearicon.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    fun Search(text: String) {
        val (searchList, _) = loadRoomConcerts().toCollection(ArrayList())
            .partition {
            it.nationality.toLowerCase().contains(text.toLowerCase()) ||
            it.ticketsLeft.toLowerCase().contains(text.toLowerCase()) ||
            it.location.toLowerCase().contains(text.toLowerCase()) ||
            it.price.toLowerCase().contains(text.toLowerCase()) ||
            it.group.toLowerCase().contains(text.toLowerCase()) ||
            it.date.toLowerCase().contains(text.toLowerCase())
        }
        refreshRecyclerView(searchList)
    }

    fun refreshRecyclerView(list: List<Concert>) {
        roomConcertsList.clear()
        roomConcertsList.addAll(list)
        userAdapter.notifyDataSetChanged()
    }

}