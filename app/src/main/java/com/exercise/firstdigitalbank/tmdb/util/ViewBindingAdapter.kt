package com.exercise.firstdigitalbank.tmdb.util

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import timber.log.Timber

@BindingAdapter("view:drawBackgroundColor")
fun View.setViewBackgroundColor(colorString: String?) {
    colorString?.let {
        try {
            background = ColorDrawable(Color.parseColor(colorString))
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
        }
    }
}

@BindingAdapter(
    value = ["imageView:imageUrl", "imageView:bitmap", "imageView:placeholderRes", "imageView:errorRes", "imageView:circleCrop"],
    requireAll = false
)
fun ImageView.loadImage(
    url: String?,
    bitmap: Bitmap?,
    placeholderRes: Drawable?,
    errorRes: Drawable?,
    circleCrop: Boolean = false
) {
    val requestBuilder = Glide.with(context)
        .asBitmap()
        .load(url ?: bitmap)
    with(requestBuilder) {
        placeholderRes?.let {
            placeholder(placeholderRes)
        }
        errorRes?.let {
            error(errorRes)
                .fallback(errorRes)
        }
        if (circleCrop) circleCrop()
        transition(BitmapTransitionOptions.withCrossFade(1000))
    }.into(this)
}

/*BindingAdapter(value = ["toolbar:logoUrl", "toolbar:fallbackLogoId"], requireAll = true)
fun Toolbar.setLogoUrl(url: String?, @DrawableRes fallbackLogoId: Int?) {
    val requestOptions = RequestOptions()
        .let {
            if (fallbackLogoId != null)
                it.fallback(fallbackLogoId)
            else
                it
        }
    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .into(object : CustomTarget<Drawable>() {

            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                val bitmap = (resource as BitmapDrawable).bitmap
                val cropDrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 144 , 38, true));
                logo = cropDrawable
            }

            override fun onLoadCleared(placeholder: Drawable?) = Unit
        })
}*/
