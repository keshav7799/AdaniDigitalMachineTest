package com.adanidigital.machinetest
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adanidigital.machinetest.apimanger.ApiManager
import com.adanidigital.machinetest.models.Article
import com.adanidigital.machinetest.models.ArticleResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel  : ViewModel() {
    val articleList = MutableLiveData<List<Article>>()
    private var request: Call<ResponseBody>? = null

    fun getArticles() {
        val queryMap: MutableMap<String, Any> = HashMap()
        queryMap["q"] = "tesla"
        queryMap["from"] = "2022-07-04"
        queryMap["sortBy"] = "publishedAt"
        queryMap["apiKey"] = "ed419c2321c64a4bafcef3a1e3691c62"
        request = ApiManager.instance.searchApi(queryMap = queryMap)
        request?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                @NonNull call: Call<ResponseBody?>,
                @NonNull response: Response<ResponseBody?>,
            ) {
                if (response.isSuccessful) {
                    val jsonStr: String? =  response.body()?.string()
                    val list  = Gson().fromJson(jsonStr, ArticleResponse::class.java)
                    articleList.value = list.articles
                }
            }
            override fun onFailure(@NonNull call: Call<ResponseBody?>, @NonNull t: Throwable) {
                Log.d("rahul",t.toString())

            }
        })
    }
}


