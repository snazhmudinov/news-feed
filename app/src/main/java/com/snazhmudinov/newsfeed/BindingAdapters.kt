package com.snazhmudinov.newsfeed

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:src")
fun setImage(imageView: ImageView, path: String?) {
    Glide.with(imageView)
        .load(path)
        .placeholder(R.color.colorPrimary)
        .into(imageView)
}