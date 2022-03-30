package com.example.retrofitmyproject

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitmyproject.databinding.ActivityMainBinding
import com.example.retrofitmyproject.databinding.ActivityPostDetailBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val filmId = intent.getStringExtra("movieId")
        getFilm(id = filmId)
    }

    private fun getFilm(id: String?) {
        if (!id.isNullOrEmpty()) {
            RetrofitService.getFilmApi().getFilmById(id)
                .enqueue(object : retrofit2.Callback<Result> {
                    override fun onFailure(call: Call<Result>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<Result>, response: Response<Result>) {
                        val film = response.body()
                        if (film != null) {
                            binding.tvTitle.text = film.original_title
                        }
                    }
                })
        }
    }
}
