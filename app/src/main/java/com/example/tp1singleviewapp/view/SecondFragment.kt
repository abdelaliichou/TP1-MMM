package com.example.tp1singleviewapp.view

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import com.example.tp1singleviewapp.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tp1singleviewapp.animations.ISTICAnimation
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
        applyAnimations()
        receiveData()
        // binding.buttonSecond.setOnClickListener {  findNavController().navigate(R.id.second_to_first) }
    }

    fun receiveData() {
        val args: SecondFragmentArgs by navArgs()
        val user = args.user
        if (user == null) return

        Log.d("DATA from viewmodel fragment", user!!.name)
        Log.d("DATA from viewmodel fragment", user.surname)
        Log.d("DATA from viewmodel fragment", user.number)
        Log.d("DATA from viewmodel fragment", user.country)
        Log.d("DATA from viewmodel fragment", user.birthday)
        Log.d("DATA from viewmodel fragment", user.email)
    }

    fun applyAnimations() {
        ISTICAnimation.fadeIn(binding.card, 0L)
        ISTICAnimation.fadeIn(binding.nameLayout, 300L)
        ISTICAnimation.fadeIn(binding.surnameLayout, 400L)
        ISTICAnimation.fadeIn(binding.emailLayout, 500L)
        ISTICAnimation.fadeIn(binding.birthdayLayout, 600L)
        ISTICAnimation.fadeIn(binding.phoneLayout, 700L)
        ISTICAnimation.fadeIn(binding.mainImg1, 800L)
        ISTICAnimation.fadeIn(binding.mainImg2, 900L)
        ISTICAnimation.fadeIn(binding.mainImg3, 1000L)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}