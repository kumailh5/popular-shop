package com.kumail.popularshop.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by kumailhussain on 15/10/2021.
 */
data class FormattedPicture(
    @SerializedName("url")
    val url: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int
)