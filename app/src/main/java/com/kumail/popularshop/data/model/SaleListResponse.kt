package com.kumail.popularshop.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by kumailhussain on 15/10/2021.
 */
data class SaleListResponse(
    @SerializedName("objects")
    val saleItems: List<SaleItem>,
    @SerializedName("meta")
    val meta: Meta
)