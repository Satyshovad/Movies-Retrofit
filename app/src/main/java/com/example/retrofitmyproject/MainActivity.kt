package com.example.retrofitmyproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmyproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Adapter.RecyclerViewItemClick {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setAdapter()
        getPosts()
    }

    private fun setAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
    }

    private fun getPosts() {
        RetrofitService.getFilmApi().getFilmList("150e91f84ab8797ad447a87d1f7160aa").enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    binding.recyclerView.adapter =
                        Adapter(list = list?.results, itemClickListener = this@MainActivity)
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun itemClick(position: Int, item: Result) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movieId", item.id)
        startActivity(intent)
    }
}