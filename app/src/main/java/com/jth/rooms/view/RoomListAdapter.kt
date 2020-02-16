package com.jth.rooms.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jth.rooms.BR
import com.jth.rooms.databinding.RoomListItemAverageBinding
import com.jth.rooms.databinding.RoomListItemTypeAptBinding
import com.jth.rooms.databinding.RoomListItemTypeOneTwoBinding
import com.jth.rooms.repo.model.Room
import com.jth.rooms.viewmodel.MainViewModel


class RoomListAdapter(val viewModel : MainViewModel) : PagedListAdapter<Room, RoomListAdapter.RoomListViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: RoomListViewHolder, position: Int) {
        when(getItemViewType(position)) {
            0 -> {
                holder.bindTypeOne(viewModel, getItem(position))
            }
            1 -> {
                holder.bindTypeApt(viewModel, getItem(position))
            }
            else -> {
                holder.bindTypeAverage(viewModel, getItem(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomListViewHolder {
        val view : View

        if(viewType == 0) {
            view = LayoutInflater.from(parent.context).inflate(com.jth.rooms.R.layout.room_list_item_type_one_two, parent, false)
        } else if(viewType == 1) {
            view = LayoutInflater.from(parent.context).inflate(com.jth.rooms.R.layout.room_list_item_type_apt, parent, false)
        } else {
            view = LayoutInflater.from(parent.context).inflate(com.jth.rooms.R.layout.room_list_item_average, parent, false)
        }

        return  RoomListViewHolder(view)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Room>() {
            override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean  {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.viewType!!
    }

    class RoomListViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

        var room : Room? = null

        fun bindTypeOne(viewModel : MainViewModel, room : Room?) {
            val binding : RoomListItemTypeOneTwoBinding = DataBindingUtil.bind(view)!!
            val item =  room

            item?.let {
                binding.setVariable(BR.RoomListItem, it)
            }

            binding.setVariable(BR.averageItem, viewModel.repo.average)

            binding.viewModel =  viewModel
        }

        fun bindTypeApt(viewModel : MainViewModel, room : Room?) {
            val binding : RoomListItemTypeAptBinding = DataBindingUtil.bind(view)!!
            val item =  room

            item?.let {
                binding.setVariable(BR.RoomListItem, it)
            }

            binding.viewModel =  viewModel
        }

        fun bindTypeAverage(viewModel : MainViewModel, room : Room?) {
            val binding : RoomListItemAverageBinding = DataBindingUtil.bind(view)!!
            val item =  room

            item?.let {
                binding.setVariable(BR.RoomListItem, it)
            }

            binding.viewModel =  viewModel
        }
    }
}