package com.example.tp1singleviewapp.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp1singleviewapp.animations.ISTICAnimation
import com.example.tp1singleviewapp.databinding.FragmentFirstBinding
import com.example.tp1singleviewapp.model.User
import com.example.tp1singleviewapp.viewModel.SharedUserViewModel
import java.util.Calendar

class FirstFragment : Fragment() {

    lateinit var email: String

    lateinit var name: String
    lateinit var surname: String
    lateinit var phone: String
    lateinit var birthday: String
    lateinit var country: String
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedUserViewModel: SharedUserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initialize the shared viewmodel variable, this page is the one who will set the shared value
        sharedUserViewModel = ViewModelProvider(requireActivity()).get(
            SharedUserViewModel::class.java
        )

        applyAnimations()
        onClicks(view)
    }

    fun chargingData() {
        email = binding.emailLayout.editText!!.text.toString()
        name = binding.nameLayout.editText!!.text.toString()
        surname = binding.prenomLayout.editText!!.text.toString()
        phone = binding.phoneLayout.text.toString()
        birthday = binding.dateLayout.editText!!.text.toString()
        country = binding.countryLayout.selectedCountryName.toString()
    }

    fun validateInputs(): Boolean {
        chargingData()
        if (email.isEmpty()) return false
        if (name.isEmpty()) return false
        if (surname.isEmpty()) return false
        if (phone.isEmpty()) return false
        if (birthday.isEmpty()) return false
        return true
    }

    fun onClicks(view: View) {

        binding.loginButton.setOnClickListener {
            if (!validateInputs()) {
                Toast.makeText(
                    view.context,
                    "Please enter all your data!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            navigate(0)
        }

        binding.infosButton.setOnClickListener {
            if (!validateInputs()) {
                Toast.makeText(
                    view.context,
                    "Please enter all your data!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            
            navigate(1)
        }

        binding.dateLayout.setStartIconOnClickListener {
            showBirthdayPicker(view.context)
        }

        binding.dateLayout.editText!!.setOnClickListener {
            showBirthdayPicker(view.context)
        }

    }

    fun navigate(index: Int) {

        val user = User(
            name,
            surname,
            email,
            phone,
            country,
            birthday
        )

        if (index == 0) {
            // passing the user as a parameter to the second fragment
            findNavController().navigate(FirstFragmentDirections.firstToViewmodel(
                    user = user
                )
            )
            return
        }

        // not passing anything to the second fragment, instead, we will update the shared user value
        sharedUserViewModel.setUser(user)
        findNavController().navigate(FirstFragmentDirections.firstToInfo(
                user = null
            )
        )
    }

    fun showBirthdayPicker(context: Context) {

        val calendar = Calendar.getInstance()

        calendar.add(Calendar.YEAR, -23)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(
            context,
            { _, y, m, d ->
                binding.dateLayout.editText!!.setText(String.format("%02d/%02d/%04d", d, m + 1, y))
            },
            year,
            month,
            day
        )

        // Restrict to [today .. 120 years ago]
        dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        calendar.add(Calendar.YEAR, -120)
        dialog.datePicker.minDate = calendar.timeInMillis

        dialog.show()
    }

    fun applyAnimations() {
        ISTICAnimation.fadeIn(binding.mainImg, 0L)
        ISTICAnimation.fadeIn(binding.nameLayout, 300L)
        ISTICAnimation.fadeIn(binding.prenomLayout, 400L)
        ISTICAnimation.fadeIn(binding.emailLayout, 500L)
        ISTICAnimation.fadeIn(binding.dateLayout, 600L)
        ISTICAnimation.fadeIn(binding.phoneParentLayout, 700L)
        ISTICAnimation.fadeIn(binding.loginButton, 800L)
        ISTICAnimation.fadeIn(binding.infosButton, 900L)
        ISTICAnimation.fadeIn(binding.mainImg1, 1000L)
        ISTICAnimation.fadeIn(binding.mainImg2, 1100L)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}