package com.kumail.popularshop.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

/**
 * Created by kumailhussain on 15/10/2021.
 */
data class Picture(
    @SerializedName("id")
    val id: Int,
    @SerializedName("formats")
    val formats: Map<String, @RawValue FormattedPicture>
)