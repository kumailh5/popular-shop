package com.kumail.popularshop.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by kumailhussain on 15/10/2021.
 */
data class Meta(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("last_offset_id")
    val lastOffsetId: String,
    @SerializedName("end")
    val end: Boolean
)