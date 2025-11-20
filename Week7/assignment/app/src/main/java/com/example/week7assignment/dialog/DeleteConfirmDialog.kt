package com.example.week7assignment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.week7assignment.databinding.DialogDeleteConfirmBinding

class DeleteConfirmDialog(
    private val title: String,
    private val message: String,
    private val onConfirm: () -> Unit,
    private val onEdit: (() -> Unit)? = null
) : DialogFragment() {

    private lateinit var binding: DialogDeleteConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog_Alert)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDeleteConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = title
        binding.tvMessage.text = message

        binding.btnDelete.setOnClickListener {
            onConfirm()
            dismiss()
        }

        binding.btnEdit.setOnClickListener {
            onEdit?.invoke()
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}

