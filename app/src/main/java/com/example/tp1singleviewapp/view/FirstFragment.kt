package com.example.tp1singleviewapp.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.fragment.findNavController
import com.example.tp1singleviewapp.R
import com.example.tp1singleviewapp.databinding.FragmentFirstBinding
import com.example.tp1singleviewapp.model.User
import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker
import java.util.Calendar


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var emailLayout: TextInputLayout
    lateinit var nameLayout: TextInputLayout
    lateinit var surnameLayout: TextInputLayout
    lateinit var phoneLayout: TextInputLayout
    lateinit var birthdayLayout: TextInputLayout
    lateinit var loginButton: RelativeLayout
    lateinit var countryPicker: CountryCodePicker

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        initialisation(binding.root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks(view)
    }

    fun onClicks(view: View) {
        loginButton.setOnClickListener {
            if (!validateInputs()) {
                Toast.makeText(
                    view.context,
                    "Please enter all your data!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            navigate()
        }

        birthdayLayout.setStartIconOnClickListener {
            showBirthdayPicker(view.context)
        }

        birthdayLayout.editText!!.setOnClickListener {
            showBirthdayPicker(view.context)
        }
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
                birthdayLayout.editText!!.setText(String.format("%02d/%02d/%04d", d, m + 1, y))
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

    fun navigate() {
        var user = User(
            nameLayout.editText!!.text.toString(),
            surnameLayout.editText!!.text.toString(),
            emailLayout.editText!!.text.toString(),
            phoneLayout.editText!!.text.toString().toInt(),
            countryPicker.selectedCountryName,
            birthdayLayout.editText!!.text.toString()
        )

        val action = FirstFragmentDirections.firstToSecond(user = user)
        findNavController().navigate(action)
    }

    fun validateInputs(): Boolean {
        if (emailLayout.editText!!.text.isEmpty()) return false
        if (nameLayout.editText!!.text.isEmpty()) return false
        if (surnameLayout.editText!!.text.isEmpty()) return false
        if (phoneLayout.editText!!.text.isEmpty()) return false
        if (birthdayLayout.editText!!.text.isEmpty()) return false
        return true
    }

    fun initialisation(view: View) {
        emailLayout = view.findViewById(R.id.email_layout)
        nameLayout = view.findViewById(R.id.name_layout)
        surnameLayout = view.findViewById(R.id.prenom_layout)
        phoneLayout = view.findViewById(R.id.phone_layout)
        birthdayLayout = view.findViewById(R.id.date_layout)
        loginButton = view.findViewById(R.id.login_button)
        countryPicker = view.findViewById(R.id.country_layout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}