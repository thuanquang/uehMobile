package com.example.bai4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bai4.databinding.ItemTaskBinding

class TaskAdapter(
    private val tasks: MutableList<TaskModel>,
    private val onItemClick: (TaskModel, Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskModel) {
            binding.textTitle.text = task.title
            binding.textDescription.text = task.description
            binding.root.setOnClickListener {
                onItemClick(task, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<TaskModel>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    fun addTask(task: TaskModel) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun updateTask(position: Int, task: TaskModel) {
        tasks[position] = task
        notifyItemChanged(position)
    }
}

