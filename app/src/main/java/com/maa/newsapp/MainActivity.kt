package com.maa.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maa.newsapp.adapter.RecyclerViewAdapter
import com.maa.newsapp.databinding.ActivityMainBinding
import com.maa.newsapp.model.News
import com.maa.newsapp.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    var itemList= ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView= findViewById<RecyclerView>(R.id.recyclerView)
        val adapter= RecyclerViewAdapter(itemList)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.getData()

        viewModel.mutable.observe(this, Observer {
            itemList.clear()
            for(i in 0 until it.size){
                itemList.add(News(it[i].title,it[i].description,it[i].url,it[i].urlToImage,it[i].publishedAt))
            }
            adapter.notifyDataSetChanged()
        })
    }
}