package com.maa.newsapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.maa.mvvm.remote.APIService
import com.maa.mvvm.remote.RetroClass
import com.maa.newsapp.model.Articles
import com.maa.newsapp.model.News
import retrofit2.Response

class Repository {
    val mutable:MutableLiveData<List<News>> = MutableLiveData()
    val arrayList:ArrayList<News> = ArrayList()

    fun getData(){
        val apiService: APIService = RetroClass().apiService()
        val call = apiService.getData("v2/top-headlines?country=tr&apiKey=fc4044a38b02414ea8aa2b4c82692cb0")
        call.enqueue(object : retrofit2.Callback<Articles?> {
            override fun onResponse(call: retrofit2.Call<Articles?>, response: Response<Articles?>) {
                for(i in 0 until response.body()!!.articles.size){
                    arrayList.add(News(response.body()!!.articles[i].title,response.body()!!.articles[i].description,response.body()!!.articles[i].url,
                        response.body()!!.articles[i].urlToImage,response.body()!!.articles[i].publishedAt))
                }
                mutable.value=arrayList
            }

            override fun onFailure(call: retrofit2.Call<Articles?>, t: Throwable) {
                Log.d("Retro Error",t.toString())
            }
        })
    }

    fun getMutableLiveData():MutableLiveData<List<News>>{
        return mutable
    }
}
