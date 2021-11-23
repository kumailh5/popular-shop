package com.kumail.popularshop.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import java.util.*

/**
 * Created by kumailhussain on 15/10/2021.
 */
data class SaleItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("brand_id")
    val brandId: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("active_status")
    val activeStatus: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("categories")
    val categories: List<Int>,
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("international_shipping_cost")
    val internationalShippingCost: Double,
    @SerializedName("national_shipping_cost")
    val nationalShippingCost: Double,
    @SerializedName("pictures_data")
    val pictures: @RawValue List<Picture>,
    @SerializedName("price_amount")
    val priceAmount: BigDecimal,
    @SerializedName("price_currency")
    val priceCurrency: String,
    @SerializedName("pub_date")
    val pubDate: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("purchase_via_paypal")
    val isPaypalPurchase: Boolean,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("variant_set")
    val variantSet: Int,
    @SerializedName("variants")
    val variants: Map<String, Int>,
    @SerializedName("hand_delivery")
    val isHandDelivery: Boolean,
    @SerializedName("user_data")
    val userData: Map<String, Int>
)

fun SaleItem.getPrice(): String =
    String.format("%s%.2f", Currency.getInstance(this.priceCurrency).symbol, this.priceAmount)
