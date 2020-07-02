package com.ebutor.kotlin.binding

import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (!isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("isEmptyGone")
fun isEmptyGone(view: TextView, data: String?) {
    view.visibility = if (data == null || data.trim().isEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}

@BindingAdapter("src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}


@BindingAdapter("subTitle")
fun setSubTitle(tv: TextView, data: String) {
    if (!data.trim().isEmpty()) {
        tv.text = data
        tv.visibility = View.VISIBLE
    } else {
        tv.visibility = View.GONE
    }
}

@BindingAdapter("adapter")
fun setAdapter(rv: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    rv.adapter = adapter
}


@BindingAdapter("spannable")
fun setsSpannble(tv: TextView, strname: String) {
    Log.e("MainActivity", "custom function called")
    val nameCaps = strname
    if (nameCaps != null)
        tv.setText(nameCaps)

}

@BindingAdapter("spanTitle", "spanText")
fun namespan(tv: TextView, spanTitle: String, spanText: String) {
    Log.e(spanText, spanTitle);
}


