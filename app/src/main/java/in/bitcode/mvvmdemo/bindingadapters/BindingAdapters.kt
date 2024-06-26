package `in`.bitcode.mvvmdemo.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun setWebImageUrlToImageView(
    imageView : ImageView,
    imageUrl : String?
) {
    if(imageUrl == null) {
        return
    }

    Glide.with(imageView)
        .load(imageUrl)
        .into(imageView)
}

