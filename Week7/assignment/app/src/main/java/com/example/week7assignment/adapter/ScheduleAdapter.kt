package com.example.week7assignment.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.week7assignment.data.Schedule
import com.example.week7assignment.databinding.ItemScheduleBinding

class ScheduleAdapter(
    private val onScheduleClicked: (Schedule) -> Unit,
    private val onUrlClicked: (String) -> Unit
) : ListAdapter<Schedule, ScheduleAdapter.ScheduleViewHolder>(ScheduleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ScheduleViewHolder(private val binding: ItemScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schedule: Schedule) {
            binding.apply {
                tvHoTen.text = "Name: ${schedule.hoTen}"
                tvTieuDe.text = "Title: ${schedule.tieuDe}"
                tvNoiDung.text = "Content: ${schedule.noiDung}"
                tvFromTime.text = "From: ${schedule.fromTime}"
                tvToTime.text = "To: ${schedule.toTime}"

                // Setup link
                if (schedule.linkHuu.isNotEmpty()) {
                    tvLinkHuu.text = "Link: ${schedule.linkHuu}"
                    tvLinkHuu.setOnClickListener {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(schedule.linkHuu))
                            binding.root.context.startActivity(intent)
                        } catch (e: Exception) {
                            onUrlClicked(schedule.linkHuu)
                        }
                    }
                } else {
                    tvLinkHuu.text = "No link available"
                }

                // Click to show options
                root.setOnClickListener {
                    onScheduleClicked(schedule)
                }
            }
        }
    }

    private class ScheduleDiffCallback : DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }
    }
}

