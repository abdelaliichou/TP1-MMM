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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tp1singleviewapp.animations.ISTICAnimation
import com.example.tp1singleviewapp.databinding.FragmentSecondBinding
import com.example.tp1singleviewapp.model.User
import com.example.tp1singleviewapp.viewModel.SharedUserViewModel
import com.example.tp1singleviewapp.viewModel.UserViewModel
import kotlin.getValue

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private lateinit var sharedUserViewModel: SharedUserViewModel

    private val viewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        // linking the xml variable with the kotlin one
        binding.uservm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this page is the one who will observe the shared value that have been set in the previous page
        sharedUserViewModel = ViewModelProvider(requireActivity()).get(
            SharedUserViewModel::class.java
        )

        applyAnimations()
        receiveData()
    }

    fun receiveData() {
        val args: SecondFragmentArgs by navArgs()
        val user = args.user

        if (user == null) {
            // charging the xml fields with the shared user value
            observeSharedUser()
            return
        }

        // charging the xml fields with the user coming from the ViewModelFragment
        setUserViewModelVariable(user)
    }

    fun observeSharedUser() {
        sharedUserViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                setUserViewModelVariable(user)
            }
        }
    }

    fun setUserViewModelVariable(user: User) {
        viewModel.name.value = user.name
        viewModel.surname.value = user.surname
        viewModel.email.value = user.email
        viewModel.phone.value = user.number
        viewModel.birthday.value = user.birthday
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