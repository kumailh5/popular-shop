package com.kumail.popularshop.data.repository

import com.kumail.popularshop.data.model.SaleListResponse
import com.kumail.popularshop.network.ApiResponse
import javax.inject.Inject

/**
 * Created by kumailhussain on 15/10/2021.
 */
interface MainRepository {
    suspend fun getSaleList(): ApiResponse<SaleListResponse>
}

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MainRepository {

    override suspend fun getSaleList(): ApiResponse<SaleListResponse> =
        try {
            ApiResponse.create(apiService.getSaleList())
        } catch (e: Exception) {
            ApiResponse.ExceptionError(e)
        }
}