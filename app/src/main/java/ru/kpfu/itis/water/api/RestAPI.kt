package ru.kpfu.itis.water.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Melnikov Semyon on 03.06.18.
 * Higher School ITIS KFU
 */
class RestAPI {

    private val baseUrl = ""

    private val itisWaterApi: ItisWaterApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        itisWaterApi = retrofit.create(ItisWaterApi::class.java)
    }

    fun login(login: String, password: String) : Call<ItisWaterLoginResponse> =
            itisWaterApi.login(ItisWaterLoginRequest(login, password))

    fun getUserTickets(userId: Long): Call<ItisWaterTicketResponse> =
            itisWaterApi.getUserTickets(userId)
}