package com.codiyapa.getnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()

    }
    private fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
        val array = ArrayList<Datas>()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
                url,
                null,
                { response ->
                    val newsArticleArray = response.getJSONArray("articles")
                    for (i in 0 until newsArticleArray.length()){
                        val newsObject = newsArticleArray.getJSONObject(i)
                        val news = Datas(
                                newsObject.getString("title"),
                                newsObject.getString("author"),
                                newsObject.getString("url"),
                                newsObject.getString("urlToImage")
                        )
                        array.add(news)
                    }
                    val recyclerViewAdapter = RecyclerViewAdapter(this , array)
                    recyclerView.adapter = recyclerViewAdapter
                },
                { error ->
                    // TODO: Handle error
                    Toast.makeText(this , "Something Went Wrong", Toast.LENGTH_LONG).show()
                }
        )
        queue.add(jsonObjectRequest)
    }
}