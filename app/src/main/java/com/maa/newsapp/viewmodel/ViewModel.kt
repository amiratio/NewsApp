package com.maa.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.maa.newsapp.model.News
import com.maa.newsapp.repository.Repository

class ViewModel(application: Application) : AndroidViewModel(application){
    private val repository: Repository
    var mutable=MutableLiveData<List<News>>()
        get() = repository.getMutableLiveData()
    
    init {
        repository = Repository()
    }
    
    fun getData(){
        repository.getData()
    }

}