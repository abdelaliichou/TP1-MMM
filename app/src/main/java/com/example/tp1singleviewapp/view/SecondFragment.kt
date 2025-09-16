package com.example.tp1singleviewapp.view

import com.example.tp1singleviewapp.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tp1singleviewapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: SecondFragmentArgs by navArgs()
        val user = args.user
        Log.d("dataaaaaaaaa from second fragment", user!!.name)
        Log.d("dataaaaaaaaa from second fragment", user.surname)
        Log.d("dataaaaaaaaa from second fragment", user.number.toString())
        Log.d("dataaaaaaaaa from second fragment", user.country)
        Log.d("dataaaaaaaaa from second fragment", user.birthday)
        Log.d("dataaaaaaaaa from second fragment", user.email)

        // findNavController().navigate(R.id.second_to_first)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}