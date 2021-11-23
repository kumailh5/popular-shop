package com.kumail.popularshop.util

import androidx.fragment.app.Fragment
import com.kumail.popularshop.PopularShopApplication.Companion.deviceWidth
import com.kumail.popularshop.data.model.Picture

/**
 * Created by kumailhussain on 15/10/2021.
 */
fun Fragment.setToolbarTitle(title: String?) {
    activity?.title = title
}

fun Picture.getPictureUrl(): String {
    val formattedPictures = this.formats.values.sortedBy { it.width }
    for (formattedPicture in formattedPictures) {
        if (deviceWidth <= formattedPicture.width)
            return formattedPicture.url
    }
    return formattedPictures.last().url
}