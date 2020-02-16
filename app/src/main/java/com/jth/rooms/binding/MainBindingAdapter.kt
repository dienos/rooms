package com.jth.rooms.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jth.rooms.repo.model.Room
import com.jth.rooms.view.RoomListAdapter
import com.jth.rooms.viewmodel.MainViewModel

@BindingAdapter(value = ["rooms", "viewModel"])
fun setRooms(view: RecyclerView, items: PagedList<Room>?, vm: MainViewModel) {
    view.adapter?.run {
        if (this is RoomListAdapter) {
            this.submitList(items)
        }
    } ?: run {
        RoomListAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}

@BindingAdapter(value = ["img_url"])
fun setRoomImg(view: ImageView, url : String) {
    Glide.with(view.context).load(url).into(view)
}

