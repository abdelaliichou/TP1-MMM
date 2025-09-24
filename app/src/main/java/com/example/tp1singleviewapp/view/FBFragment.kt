package com.example.tp1singleviewapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp1singleviewapp.R
import com.example.tp1singleviewapp.controller.MusicAdapter
import com.example.tp1singleviewapp.controller.UserAdapter
import com.example.tp1singleviewapp.databinding.FragmentFBBinding
import com.example.tp1singleviewapp.databinding.FragmentMusicBinding
import com.example.tp1singleviewapp.databinding.FragmentSecondBinding
import com.example.tp1singleviewapp.model.Music
import com.example.tp1singleviewapp.model.User
import com.example.tp1singleviewapp.room.Concert
import com.example.tp1singleviewapp.room.RoomDB
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FBFragment : Fragment() {

    private var _binding: FragmentFBBinding? = null

    private val binding get() = _binding!!
    private lateinit var userAdapter: UserAdapter

    private var originalUsersList: List<User> = emptyList()

    private var searchJob: Job? = null

    private val database = FirebaseDatabase.getInstance().getReference("users")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadUsers()
        autoSearch()
        onCLicks()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }
    }

    fun onCLicks() {
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
                    delay(300)
                    if (s.isNullOrBlank()) {
                        refreshRecyclerView(originalUsersList)
                    } else {
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

    fun Search(query: String) {
        val filteredList = originalUsersList.filter {
            it.name.lowercase().contains(query) ||
            it.surname.lowercase().contains(query) ||
            it.country.lowercase().contains(query) ||
            it.number.lowercase().contains(query) ||
            it.birthday.lowercase().contains(query) ||
            it.email.lowercase().contains(query)
        }
        refreshRecyclerView(filteredList)
    }

    private fun loadUsers() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                originalUsersList = snapshot.children.mapNotNull { it.getValue(User::class.java) }
                refreshRecyclerView(originalUsersList) // refresh immediately
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    "Couldn't get the users from db!",
                    Toast.LENGTH_SHORT
                ).show()
                originalUsersList = emptyList()
                refreshRecyclerView(originalUsersList) // keep adapter consistent
            }
        })
    }

    private fun refreshRecyclerView(list: List<User>) {
        userAdapter.usersList = list
        userAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}