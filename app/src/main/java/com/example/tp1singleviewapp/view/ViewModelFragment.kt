package com.example.tp1singleviewapp.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tp1singleviewapp.databinding.FragmentViewModelBinding
import com.example.tp1singleviewapp.viewModel.UserViewModel
import java.util.Calendar

class ViewModelFragment : Fragment() {

    private var _binding: FragmentViewModelBinding? = null
    
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewModelBinding.inflate(
            inflater,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        // linking the xml variables with the kotlin one
        binding.uservm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks(view)
    }

    fun onClicks(view: View) {

        binding.loginButton.setOnClickListener {
            if (!viewModel.validateInputs()) {
                Toast.makeText(
                    view.context,
                    "Please enter all your data!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            navigate()
        }

        binding.dateLayout.setStartIconOnClickListener {
            showBirthdayPicker(view.context)
        }

        binding.dateLayout.editText!!.setOnClickListener {
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

    fun navigate() {

        //country is a third party library, so doing it like this is simpler
        val user = viewModel.toUser()
        user.country = binding.countryLayout.selectedCountryName.toString()

        findNavController().navigate(ViewModelFragmentDirections.viewmodelToSecond(
                user = user
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}