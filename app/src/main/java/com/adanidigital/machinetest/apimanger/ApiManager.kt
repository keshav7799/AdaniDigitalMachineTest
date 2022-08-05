package com.adanidigital.machinetest.apimanger

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap


class ApiManager {
    private val baseUrl = "http://newsapi.org/"
    private fun <T> createRetrofitService(service: Class<T>): T {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        val client: OkHttpClient = builder.build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }

    private val service: WikiApiClient
        private get() = createRetrofitService(WikiApiClient::class.java)

    private interface WikiApiClient {
        @JvmSuppressWildcards
        @GET("v2/everything")
        fun search(@QueryMap queryMap: Map<String, Any>?): Call<ResponseBody>
    }

    fun searchApi(queryMap: Map<String, Any>): Call<ResponseBody> {
        return service.search(queryMap)
    }

    companion object {
        //    private final static String api = "api.php?action=query&formatversion=2&prop=pageimages|pageterms&pilimit=3&piprop=thumbnail&wbptterms=description&redirects= 1";
        val instance = ApiManager()
    }
}