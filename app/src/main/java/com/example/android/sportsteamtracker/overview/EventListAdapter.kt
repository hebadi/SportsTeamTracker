package com.example.android.sportsteamtracker.overview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.sportsteamtracker.databinding.EventItemBinding
import com.example.android.sportsteamtracker.data.Event


class EventListAdapter : ListAdapter<Event, EventListAdapter.EventsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Event>(){
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.idEvent == newItem.idEvent
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.strResult == newItem.strResult
        }
    }

    class EventsViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(event: Event) {
            binding.event = event
            binding.date.text = event.dateEvent
            binding.eventName.text = event.strEvent
            binding.content.text = event.strResult.replace("<br>", "\n")
            val winLose = event.intHomeScore > event.intAwayScore
            val winLoseStr = if (winLose) "WIN" else "LOSE"
            binding.winLose.setBackgroundColor(if(winLose) Color.GREEN else Color.RED)
            binding.winLose.text = winLoseStr + ": ${event.intHomeScore} to ${event.intAwayScore}"
            //todo: fill out the rest of the binding with event properties
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(EventItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val currentEvent = getItem(position)
        holder.bind(currentEvent)
    }
}