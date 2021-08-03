package io.rusha.bnet_market.ui.home

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MainApi {
        @GET("./?json=1")
        fun getApps(
        ): Single<List<App>>

}