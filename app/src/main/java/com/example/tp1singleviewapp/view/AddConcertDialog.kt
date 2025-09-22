package com.example.tp1singleviewapp.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.tp1singleviewapp.R
import com.example.tp1singleviewapp.databinding.FragmentAddConcertDialogBinding
import com.example.tp1singleviewapp.room.Concert
import com.example.tp1singleviewapp.room.ConcertDao
import com.example.tp1singleviewapp.room.RoomDB
import java.util.Calendar

class AddConcertDialog : DialogFragment() {

    private var _binding: FragmentAddConcertDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var musicDao: ConcertDao

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        _binding = FragmentAddConcertDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        // Make dialog background transparent (so we see edges behind)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Adjust width to 90% of screen
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        // Center dialog
        dialog.window?.setGravity(Gravity.CENTER)

        return dialog
    }

    override fun onStart() {
        super.onStart()

        musicDao = RoomDB.getDatabase(requireContext()).concertDao()

        binding.dateLayout.editText!!.setOnClickListener {
            showBirthdayPicker(requireContext())
        }

        binding.dateLayout.setOnClickListener {
            showBirthdayPicker(requireContext())
        }

        binding.addButton.setOnClickListener {
            val concert = Concert(
                group = binding.groupeLayout.editText!!.text.toString(),
                date = binding.dateLayout.editText!!.text.toString(),
                nationality = binding.countryLayout.selectedCountryName.toString(),
                location = binding.locationLayout.editText!!.text.toString(),
                price = binding.priceLayout.editText!!.text.toString(),
                ticketsLeft = binding.ticketLayout.editText!!.text.toString(),
                favorite = false,
                Image = binding.concertLayout.editText!!.text.toString(),
            )

            musicDao.insert(concert)
            Toast.makeText(requireContext(), "Concert added!", Toast.LENGTH_SHORT).show()
            dismiss()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}