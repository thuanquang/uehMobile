package com.example.week7assignment.dialog

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.week7assignment.data.Schedule
import com.example.week7assignment.databinding.DialogAddScheduleBinding
import java.util.*

class AddScheduleDialog(
    private val initialSchedule: Schedule? = null,
    private val onSave: (Schedule) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogAddScheduleBinding
    private var selectedFromTime = ""
    private var selectedToTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load initial schedule if editing
        initialSchedule?.let {
            binding.etHoTen.setText(it.hoTen)
            binding.etTieuDe.setText(it.tieuDe)
            binding.etNoiDung.setText(it.noiDung)
            binding.etLinkHuu.setText(it.linkHuu)
            selectedFromTime = it.fromTime
            selectedToTime = it.toTime
            binding.tvFromTime.text = it.fromTime
            binding.tvToTime.text = it.toTime
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.tvFromTime.setOnClickListener {
            showTimePickerFrom()
        }

        binding.tvToTime.setOnClickListener {
            showTimePickerTo()
        }

        binding.btnSave.setOnClickListener {
            saveSchedule()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun showTimePickerFrom() {
        val calendar = Calendar.getInstance()
        val hour = if (selectedFromTime.isNotEmpty()) {
            selectedFromTime.split(":")[0].toIntOrNull() ?: calendar.get(Calendar.HOUR_OF_DAY)
        } else {
            calendar.get(Calendar.HOUR_OF_DAY)
        }
        val minute = if (selectedFromTime.isNotEmpty()) {
            selectedFromTime.split(":")[1].toIntOrNull() ?: calendar.get(Calendar.MINUTE)
        } else {
            calendar.get(Calendar.MINUTE)
        }

        TimePickerDialog(
            requireContext(),
            { _, h, m ->
                selectedFromTime = String.format("%02d:%02d", h, m)
                binding.tvFromTime.text = selectedFromTime
            },
            hour,
            minute,
            true
        ).show()
    }

    private fun showTimePickerTo() {
        val calendar = Calendar.getInstance()
        val hour = if (selectedToTime.isNotEmpty()) {
            selectedToTime.split(":")[0].toIntOrNull() ?: calendar.get(Calendar.HOUR_OF_DAY)
        } else {
            calendar.get(Calendar.HOUR_OF_DAY)
        }
        val minute = if (selectedToTime.isNotEmpty()) {
            selectedToTime.split(":")[1].toIntOrNull() ?: calendar.get(Calendar.MINUTE)
        } else {
            calendar.get(Calendar.MINUTE)
        }

        TimePickerDialog(
            requireContext(),
            { _, h, m ->
                selectedToTime = String.format("%02d:%02d", h, m)
                binding.tvToTime.text = selectedToTime
            },
            hour,
            minute,
            true
        ).show()
    }

    private fun saveSchedule() {
        val hoTen = binding.etHoTen.text.toString().trim()
        val tieuDe = binding.etTieuDe.text.toString().trim()
        val noiDung = binding.etNoiDung.text.toString().trim()
        val linkHuu = binding.etLinkHuu.text.toString().trim()

        if (hoTen.isEmpty() || tieuDe.isEmpty() || selectedFromTime.isEmpty() || selectedToTime.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val schedule = Schedule(
            id = initialSchedule?.id ?: 0,
            hoTen = hoTen,
            tieuDe = tieuDe,
            noiDung = noiDung,
            fromTime = selectedFromTime,
            toTime = selectedToTime,
            linkHuu = linkHuu
        )

        onSave(schedule)
        dismiss()
    }
}

