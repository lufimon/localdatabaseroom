package th.co.cdgs.mobile.local_database_room

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.co.cdgs.mobile.local_database_room.remote.Api

object Service {

    val okHttpClient = OkHttpClient.Builder().build()

    fun getRetrofitService(): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.100.150.155:8089/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(Api::class.java)
    }
}