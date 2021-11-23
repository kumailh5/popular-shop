package com.kumail.popularshop.data.repository

import com.kumail.popularshop.data.model.SaleListResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by kumailhussain on 15/10/2021.
 */
interface ApiService {
    @GET("products/popular/?offset_id=")
    suspend fun getSaleList(): Response<SaleListResponse>
}