package com.jay.mvvmdaggerkotlin.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Jay on 17/06/2019.
 *
 *
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setError(view: TextInputLayout?, errorMessage: String?) {
        if (!errorMessage.isNullOrBlank())
            view?.error = errorMessage
    }

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    //Add your future BindingAdapters here...

}
