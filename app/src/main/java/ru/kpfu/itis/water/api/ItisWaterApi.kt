package ru.kpfu.itis.water.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Melnikov Semyon on 03.06.18.
 * Higher School ITIS KFU
 */
interface ItisWaterApi {

    @POST("/client/login")
    fun login(@Body userData: ItisWaterLoginRequest): Call<ItisWaterLoginResponse>

    @GET("/client/tickets")
    fun getUserTickets(@Query("userId") userId: Long): Call<ItisWaterTicketResponse>
}