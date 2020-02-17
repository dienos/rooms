package com.jth.rooms.binding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jth.rooms.R

@BindingAdapter(value = ["hash_tag"])
fun setHashTag(view: LinearLayout, hashTags: ArrayList<String>) {
    val inflater =
        view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    if(view.childCount > 0) {
        view.removeAllViews()
    }

    if (hashTags.size <= 3) {
        for (i in hashTags.indices) {
            val hashTagView = inflater.inflate(R.layout.hash_tag, null)
            addHashTagView(hashTags[i], view, hashTagView)
        }
    } else {
        for (j in 0..2) {
            val hashTagView = inflater.inflate(R.layout.hash_tag, null)
            addHashTagView(hashTags[j], view, hashTagView)
        }

        val hashTagView = inflater.inflate(R.layout.hash_tag, null)
        addHashTagView("...", view, hashTagView)
    }
}

private fun addHashTagView(hashText: String, rootView: LinearLayout, childView: View) {
    val hashTextView: TextView = childView.findViewById(R.id.tag)
    hashTextView.text = hashText
    rootView.addView(childView)
}

@BindingAdapter(value = ["img_url"])
fun setRoomImg(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}

