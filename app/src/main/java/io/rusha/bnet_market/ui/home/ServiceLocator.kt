package io.rusha.bnet_market.ui.home

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    private val retrofit = Retrofit.Builder().baseUrl("http://bnet.i-partner.ru/projects/playMarket/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMainApi(): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}